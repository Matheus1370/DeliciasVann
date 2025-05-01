package com.deliciasvann.delicias_vann.cadastros.model.dto;

import lombok.Data;

@Data
public class UsersRequest {
    private String email;
    private String password;
    private String role;
}
