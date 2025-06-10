package com.deliciasvann.delicias_vann.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyRequest {
    
    private String email;
    private String password;

}
