package com.deliciasvann.delicias_vann.modules.user.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.deliciasvann.delicias_vann.modules.user.UsersEntity;

import lombok.Data;

@Data
public class UsersResponse {

    private UUID id;
    private String email;
    private String role;
    private LocalDateTime createdAt;

    public UsersResponse(UsersEntity user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
    }
}
