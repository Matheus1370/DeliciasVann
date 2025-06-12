package com.deliciasvann.delicias_vann.modules.customer.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliciasvann.delicias_vann.modules.customer.dto.AuthCustomerRequest;
import com.deliciasvann.delicias_vann.modules.customer.service.AuthCustomerService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customer")
public class AuthCustomerController {

    @Autowired
    private AuthCustomerService authService;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCustomerRequest request) {
        try {
            var token = this.authService.execute(request);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
