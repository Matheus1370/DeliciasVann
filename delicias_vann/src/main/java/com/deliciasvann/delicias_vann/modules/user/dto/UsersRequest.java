package com.deliciasvann.delicias_vann.modules.user.dto;

import lombok.Data;

@Data
public class UsersRequest {
    private String email;
    private String password;
    private String role;
}
