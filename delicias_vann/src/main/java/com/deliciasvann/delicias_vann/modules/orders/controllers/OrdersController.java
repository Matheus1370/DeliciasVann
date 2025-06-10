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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliciasvann.delicias_vann.modules.orders.dto.OrdersRequest;
import com.deliciasvann.delicias_vann.modules.orders.dto.OrdersResponse;
import com.deliciasvann.delicias_vann.modules.orders.services.OrdersService;
import com.deliciasvann.delicias_vann.shared.enums.OrderStatus;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/orders")
public class OrdersController {
    
    @Autowired
    private OrdersService ordersService;

    @PostMapping
    public ResponseEntity<UUID> create(@RequestBody OrdersRequest request) {
        UUID id = ordersService.create(request);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(ordersService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrdersResponse>> findAll() {
        return ResponseEntity.ok(ordersService.findAll());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<UUID> updateStatus(@PathVariable UUID id, @RequestParam OrderStatus status) {
        return ResponseEntity.ok(ordersService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        ordersService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
