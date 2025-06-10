package com.deliciasvann.delicias_vann.modules.company.dto;

import com.deliciasvann.delicias_vann.modules.user.dto.UsersRequest;

import lombok.Data;

@Data
public class CompanyUserRequest {
    
    private CompanyRequest companyRequest;
    private UsersRequest usersRequest;
    
}
