package com.deliciasvann.delicias_vann.modules.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthEmployeeRequest {
    
    private String email;
    private String password;

}
