package com.deliciasvann.delicias_vann.modules.employee.controller;

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

import com.deliciasvann.delicias_vann.modules.employee.dto.EmployeeRequest;
import com.deliciasvann.delicias_vann.modules.employee.dto.EmployeeResponse;
import com.deliciasvann.delicias_vann.modules.employee.dto.EmployeeUserRequest;
import com.deliciasvann.delicias_vann.modules.employee.service.EmployeeService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/employee")
public class EmployeeController { 
    
    @Autowired
    private EmployeeService service;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('COMPANY', 'EMPLOYEE')")
    public ResponseEntity<EmployeeResponse> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('COMPANY', 'EMPLOYEE')")
    public ResponseEntity<List<EmployeeResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('COMPANY', 'EMPLOYEE')")
    public ResponseEntity<?> create(@RequestBody EmployeeUserRequest request) {
        try {
            EmployeeResponse result = service.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                new com.deliciasvann.delicias_vann.exceptions.ErrorResponse(
                    "Erro ao criar funcionário",
                    e.getMessage()
                )
            );
        }
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<UUID> update(@PathVariable UUID id, @RequestBody EmployeeRequest request) {
        try {
            UUID result = service.update(id, request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('COMPANY', 'EMPLOYEE')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
