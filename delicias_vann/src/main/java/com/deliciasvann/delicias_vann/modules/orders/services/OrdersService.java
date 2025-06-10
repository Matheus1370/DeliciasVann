package com.deliciasvann.delicias_vann.modules.orders.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliciasvann.delicias_vann.modules.customer.CustomerEntity;
import com.deliciasvann.delicias_vann.modules.customer.CustomerRepository;
import com.deliciasvann.delicias_vann.modules.orders.dto.OrderItemRequest;
import com.deliciasvann.delicias_vann.modules.orders.dto.OrderItemResponse;
import com.deliciasvann.delicias_vann.modules.orders.dto.OrdersRequest;
import com.deliciasvann.delicias_vann.modules.orders.dto.OrdersResponse;
import com.deliciasvann.delicias_vann.modules.orders.entities.OrderItemEntity;
import com.deliciasvann.delicias_vann.modules.orders.entities.OrdersEntity;
import com.deliciasvann.delicias_vann.modules.orders.repositories.OrdersRepository;
import com.deliciasvann.delicias_vann.modules.product.ProductEntity;
import com.deliciasvann.delicias_vann.modules.product.ProductRepository;
import com.deliciasvann.delicias_vann.shared.enums.OrderStatus;

@Service
public class OrdersService {
    
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public UUID create(OrdersRequest request) {
        OrdersEntity order = new OrdersEntity();
        order.setStatus(OrderStatus.CREATED);

        CustomerEntity customer = customerRepository.findById(request.getCustomerId())
            .orElseThrow(() -> new RuntimeException("Customer not found"));
        order.setCustomer(customer);

        List<OrderItemEntity> orderItems = new ArrayList<>();
        for (OrderItemRequest itemRequest : request.getItems()) {
            ProductEntity product = productRepository.findById(itemRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItemEntity item = new OrderItemEntity();
            item.setOrder(order);
            item.setProduct(product);
            item.setPrice(product.getPrice());
            item.setQuantity(itemRequest.getQuantity());

            orderItems.add(item);
        }
        order.setItems(orderItems);
        order.calculateTotalPrice();

        return ordersRepository.save(order).getId();
    }

    public OrdersResponse findById(UUID id) {
        OrdersEntity order = ordersRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));

        OrdersResponse response = new OrdersResponse();
        response.setId(order.getId());
        response.setCustomerId(order.getCustomer().getId());
        response.setTotalPrice(order.getTotalPrice());
        response.setStatus(order.getStatus());

        List<OrderItemResponse> itemResponses = new ArrayList<>();
        for (OrderItemEntity item : order.getItems()) {
            OrderItemResponse itemResponse = new OrderItemResponse();
            itemResponse.setId(item.getId());
            itemResponse.setProductId(item.getProduct().getId());
            itemResponse.setProductName(item.getProduct().getName());
            itemResponse.setQuantity(item.getQuantity());
            itemResponse.setPrice(item.getPrice());
            itemResponses.add(itemResponse);
        }
        response.setItems(itemResponses);

        return response;
    }

    public List<OrdersResponse> findAll() {
        List<OrdersEntity> orders = ordersRepository.findAll();
        List<OrdersResponse> responses = new ArrayList<>();

        for (OrdersEntity order : orders) {
            responses.add(findById(order.getId()));
        }

        return responses;
    }

    public void delete(UUID id) {
        ordersRepository.deleteById(id);
    }

    public UUID updateStatus(UUID id, OrderStatus status) {
        OrdersEntity order = ordersRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));

        order.updateStatus(status);
        return ordersRepository.save(order).getId();
    }
}
