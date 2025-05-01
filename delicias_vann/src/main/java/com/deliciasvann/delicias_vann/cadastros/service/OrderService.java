package com.deliciasvann.delicias_vann.cadastros.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliciasvann.delicias_vann.cadastros.model.dto.OrderItemRequest;
import com.deliciasvann.delicias_vann.cadastros.model.dto.OrderItemResponse;
import com.deliciasvann.delicias_vann.cadastros.model.dto.OrderRequest;
import com.deliciasvann.delicias_vann.cadastros.model.dto.OrderResponse;
import com.deliciasvann.delicias_vann.cadastros.model.entity.CustomerEntity;
import com.deliciasvann.delicias_vann.cadastros.model.entity.OrderEntity;
import com.deliciasvann.delicias_vann.cadastros.model.entity.OrderItemEntity;
import com.deliciasvann.delicias_vann.cadastros.model.entity.ProductEntity;
import com.deliciasvann.delicias_vann.cadastros.model.enums.OrderStatus;
import com.deliciasvann.delicias_vann.cadastros.repository.CustomerRepository;
import com.deliciasvann.delicias_vann.cadastros.repository.OrderRepository;
import com.deliciasvann.delicias_vann.cadastros.repository.ProductRepository;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public UUID create(OrderRequest request) {
        OrderEntity order = new OrderEntity();
        order.setOrderDate(LocalDateTime.now());
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

        return orderRepository.save(order).getId();
    }

    public OrderResponse findById(UUID id) {
        OrderEntity order = orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setCustomerId(order.getCustomer().getId());
        response.setTotalPrice(order.getTotalPrice());
        response.setStatus(order.getStatus());
        response.setOrderDate(order.getOrderDate());

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

    public List<OrderResponse> findAll() {
        List<OrderEntity> orders = orderRepository.findAll();
        List<OrderResponse> responses = new ArrayList<>();

        for (OrderEntity order : orders) {
            responses.add(findById(order.getId()));
        }

        return responses;
    }

    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }

    public UUID updateStatus(UUID id, OrderStatus status) {
        OrderEntity order = orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));

        order.updateStatus(status);
        return orderRepository.save(order).getId();
    }
}
