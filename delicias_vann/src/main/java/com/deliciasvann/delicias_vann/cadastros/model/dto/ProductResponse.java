package com.deliciasvann.delicias_vann.cadastros.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.deliciasvann.delicias_vann.cadastros.model.entity.ProductEntity;

import lombok.Data;

@Data
public class ProductResponse {
    private UUID id;
    private String name;
    private String description;
    private boolean available;
    private String category;
    private Integer stock;
    private CompanyResponse company;
    private LocalDateTime createdAt;

    public ProductResponse(ProductEntity product){
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.available = product.isAvailable();
        this.category = product.getCategory();
        this.stock = product.getStock();
        this.company = new CompanyResponse(product.getCompany());
        this.createdAt = product.getCreatedAt();
    }
}
