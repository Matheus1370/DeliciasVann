package com.deliciasvann.delicias_vann.modules.customer.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class CustomerResponse{
    private UUID id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;
}
