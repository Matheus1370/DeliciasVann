package com.deliciasvann.delicias_vann.modules.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCustomerResponse {
    
    private String access_token;
    private Long expires_in;

}
