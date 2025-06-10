package com.deliciasvann.delicias_vann.modules.orders.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class OrderItemRequest {
    private UUID productId;
    private Integer quantity;
}
