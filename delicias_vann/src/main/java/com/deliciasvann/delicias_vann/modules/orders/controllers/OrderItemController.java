package com.deliciasvann.delicias_vann.modules.orders.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliciasvann.delicias_vann.modules.orders.entities.OrderItemEntity;
import com.deliciasvann.delicias_vann.modules.orders.services.OrderItemService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/order-item")
public class OrderItemController {
    @Autowired
    private OrderItemService service;

    @PostMapping
    public ResponseEntity<UUID> create(@RequestBody OrderItemEntity item) {
        return ResponseEntity.ok(service.create(item));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemEntity> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderItemEntity>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemEntity> update(@PathVariable UUID id, @RequestBody OrderItemEntity item) {
        return ResponseEntity.ok(service.update(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
