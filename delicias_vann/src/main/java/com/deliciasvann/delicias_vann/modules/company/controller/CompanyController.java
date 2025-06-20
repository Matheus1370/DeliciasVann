package com.deliciasvann.delicias_vann.modules.company.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliciasvann.delicias_vann.exceptions.ErrorResponse;
import com.deliciasvann.delicias_vann.modules.company.CompanyEntity;
import com.deliciasvann.delicias_vann.modules.company.dto.CompanyRequest;
import com.deliciasvann.delicias_vann.modules.company.dto.CompanyResponse;
import com.deliciasvann.delicias_vann.modules.company.dto.CompanyUserRequest;
import com.deliciasvann.delicias_vann.modules.company.service.CompanyService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/company")
public class CompanyController {
    
    @Autowired
    private CompanyService service;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<CompanyResponse> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<List<CompanyResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CompanyUserRequest request) {
        try {
            CompanyResponse createdCompany = service.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ErrorResponse("Erro ao criar empresa", e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<CompanyEntity> update(@PathVariable UUID id, @RequestBody CompanyRequest request) {
        try {
            CompanyEntity result = service.update(id, request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
