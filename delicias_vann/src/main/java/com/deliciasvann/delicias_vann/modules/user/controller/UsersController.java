package com.deliciasvann.delicias_vann.modules.user.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliciasvann.delicias_vann.modules.user.UsersEntity;
import com.deliciasvann.delicias_vann.modules.user.dto.UsersRequest;
import com.deliciasvann.delicias_vann.modules.user.dto.UsersResponse;
import com.deliciasvann.delicias_vann.modules.user.service.UsersService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/user")
public class UsersController {
    
    @Autowired
    private UsersService service;
    
    @PostMapping
    public UsersEntity create(@RequestBody UsersRequest request) {
        return service.create(request);
    }
    
    @PutMapping("/{id}")
    public UUID update(@PathVariable UUID id, @RequestBody UsersRequest request) {
        return service.update(id, request);
    }
    
    @GetMapping("/{id}")
    public UsersResponse findById(@PathVariable UUID id) {
        return service.findById(id);
    }
    
    @GetMapping
    public List<UsersResponse> findAll() {
        return service.findAll();
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
