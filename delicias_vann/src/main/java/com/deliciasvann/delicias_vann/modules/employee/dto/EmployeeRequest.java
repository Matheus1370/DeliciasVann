package com.deliciasvann.delicias_vann.modules.employee.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class EmployeeRequest {
    private String name;
    private String address;
    private String phone;
    private UUID companyId;
}
