package com.deliciasvann.delicias_vann.modules.employee.dto;

import java.util.UUID;

import com.deliciasvann.delicias_vann.modules.company.dto.CompanyResponse;
import com.deliciasvann.delicias_vann.modules.employee.EmployeeEntity;
import com.deliciasvann.delicias_vann.modules.user.dto.UsersResponse;

import lombok.Data;

@Data
public class EmployeeResponse {
    private UUID id;
    private String name;
    private String address;
    private String phone;
    private CompanyResponse companyResponse;
    private UsersResponse usersResponse;

    public EmployeeResponse (EmployeeEntity employee){
        this.id = employee.getId();
        this.name = employee.getName();
        this.address = employee.getAddress();
        this.phone = employee.getPhone();
        this.companyResponse = new CompanyResponse(employee.getCompany());
        this.usersResponse = new UsersResponse(employee.getUser());
    }
}
