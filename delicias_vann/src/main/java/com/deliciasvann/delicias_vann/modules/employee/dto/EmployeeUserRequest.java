package com.deliciasvann.delicias_vann.modules.employee.dto;

import com.deliciasvann.delicias_vann.modules.user.dto.UsersRequest;

import lombok.Data;

@Data
public class EmployeeUserRequest {
    private EmployeeRequest employeeRequest;
    private UsersRequest usersRequest;
}
