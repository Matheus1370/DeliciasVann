package com.deliciasvann.delicias_vann.modules.orders.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliciasvann.delicias_vann.modules.orders.entities.OrderItemEntity;
import com.deliciasvann.delicias_vann.modules.orders.repositories.OrderItemRepository;

@Service
public class OrderItemService {
    
    @Autowired
    private OrderItemRepository repository;

    public UUID create(OrderItemEntity item) {
        return repository.save(item).getId();
    }

    public OrderItemEntity findById(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    public List<OrderItemEntity> findAll() {
        return repository.findAll();
    }

    public OrderItemEntity update(UUID id, OrderItemEntity item) {
        OrderItemEntity existing = repository.findById(id).orElseThrow();
        existing.setProduct(item.getProduct());
        existing.setQuantity(item.getQuantity());
        existing.setPrice(item.getPrice());
        return repository.save(existing);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
