package com.deliciasvann.delicias_vann.cadastros.model.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class OrderItemRequest {
    private UUID productId;
    private Integer quantity;
}
