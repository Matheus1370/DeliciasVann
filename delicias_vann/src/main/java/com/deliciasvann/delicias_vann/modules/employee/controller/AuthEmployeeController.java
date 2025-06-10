package com.deliciasvann.delicias_vann.modules.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliciasvann.delicias_vann.modules.employee.dto.AuthEmployeeRequest;
import com.deliciasvann.delicias_vann.modules.employee.service.AuthEmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/employee")
public class AuthEmployeeController {
    
    @Autowired
    private AuthEmployeeService service;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthEmployeeRequest request) {
        try {
            var token = service.execute(request);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
    

}
