package com.deliciasvann.delicias_vann.cadastros.model.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private boolean available;
    private String category;
    private Integer stock;
    private String imageUrl;
    private Double price;
    private UUID companyId;
}
