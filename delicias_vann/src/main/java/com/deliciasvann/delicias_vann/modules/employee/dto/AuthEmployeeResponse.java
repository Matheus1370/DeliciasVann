package com.deliciasvann.delicias_vann.modules.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthEmployeeResponse {
    
    private String access_token;
    private Long expires_in;

}
