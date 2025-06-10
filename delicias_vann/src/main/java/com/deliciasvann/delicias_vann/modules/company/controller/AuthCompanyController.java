package com.deliciasvann.delicias_vann.modules.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliciasvann.delicias_vann.modules.company.dto.AuthCompanyRequest;
import com.deliciasvann.delicias_vann.modules.company.service.AuthCompanyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/company")
public class AuthCompanyController {
    
    @Autowired
    private AuthCompanyService service;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCompanyRequest request) {
        try {
            var token = service.execute(request);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        
    }
    

}
