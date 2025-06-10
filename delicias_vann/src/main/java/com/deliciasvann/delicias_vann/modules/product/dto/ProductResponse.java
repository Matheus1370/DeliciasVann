package com.deliciasvann.delicias_vann.modules.product.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.deliciasvann.delicias_vann.modules.company.dto.CompanyResponse;
import com.deliciasvann.delicias_vann.modules.product.ProductEntity;

import lombok.Data;

@Data
public class ProductResponse {
    private UUID id;
    private String name;
    private String description;
    private boolean available;
    private String category;
    private Integer stock;
    private String imageUrl;
    private CompanyResponse company;
    private LocalDateTime createdAt;
    private Double price;

    public ProductResponse(ProductEntity product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.available = product.isAvailable();
        this.category = product.getCategory();
        this.stock = product.getStock();
        this.company = new CompanyResponse(product.getCompany());
        this.createdAt = product.getCreatedAt();
        this.imageUrl = product.getImageUrl();
        this.price = product.getPrice();
    }
}
