package com.deliciasvann.delicias_vann.modules.company.dto;

import java.util.UUID;

import com.deliciasvann.delicias_vann.modules.company.CompanyEntity;
import com.deliciasvann.delicias_vann.modules.user.dto.UsersResponse;

import lombok.Data;

@Data
public class CompanyResponse {

    private UUID id;
    private String name;
    private String phone;
    private String address;
    private UsersResponse user;

    public CompanyResponse(CompanyEntity company) {
        this.id = company.getId();
        this.name = company.getName();
        this.phone = company.getPhone();
        this.address = company.getAddress();
        this.user = new UsersResponse(company.getUser()); // Certifique-se que esse construtor existe também
    }
}
