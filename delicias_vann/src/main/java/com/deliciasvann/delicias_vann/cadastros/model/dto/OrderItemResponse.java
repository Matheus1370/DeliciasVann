package com.deliciasvann.delicias_vann.cadastros.model.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class OrderItemResponse {
    private UUID id;
    private UUID productId;
    private String productName;
    private Integer quantity;
    private Double price;
}
