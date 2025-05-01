package com.deliciasvann.delicias_vann.cadastros.model.dto;

import java.util.UUID;

import com.deliciasvann.delicias_vann.cadastros.model.entity.EmployeeEntity;

import lombok.Data;

@Data
public class EmployeeResponse {
    private UUID id;
    private String name;
    private String address;
    private String phone;

    public EmployeeResponse (EmployeeEntity employee){
        this.id = employee.getId();
        this.name = employee.getName();
        this.address = employee.getAddress();
        this.phone = employee.getPhone();
    }
}
