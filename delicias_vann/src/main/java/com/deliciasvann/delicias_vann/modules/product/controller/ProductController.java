package com.deliciasvann.delicias_vann.modules.product.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.deliciasvann.delicias_vann.modules.product.dto.ProductRequest;
import com.deliciasvann.delicias_vann.modules.product.dto.ProductResponse;
import com.deliciasvann.delicias_vann.modules.product.service.ProductService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    ProductService service;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProductRequest request) {
        try {
            ProductResponse result = service.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                new com.deliciasvann.delicias_vann.exceptions.ErrorResponse(
                    "Erro ao criar produto",
                    e.getMessage()
                )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new com.deliciasvann.delicias_vann.exceptions.ErrorResponse(
                    "Erro inesperado ao criar produto",
                    e.getMessage()
                )
            );
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UUID> update(@PathVariable UUID id, @RequestBody ProductRequest request) {
        try {
            UUID result = service.update(id, request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
