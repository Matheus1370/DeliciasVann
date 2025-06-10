package com.deliciasvann.delicias_vann.modules.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCustomerRequest {
    private String email;
    private String password;

    
}
