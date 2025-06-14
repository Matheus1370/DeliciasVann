package com.deliciasvann.delicias_vann.modules.product;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.deliciasvann.delicias_vann.modules.company.CompanyEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false)
    private String name;

    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    @Column(length = 500)
    private String description;

    @Min(value = 0, message = "O preço não pode ser negativo")
    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private boolean available;

    @NotBlank(message = "A categoria é obrigatória")
    @Column(nullable = false)
    private String category;

    @Min(value = 0, message = "O estoque não pode ser negativo")
    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false, updatable = false)
    private CompanyEntity company;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "image_url")
    private String imageUrl;

    public void setStock(Integer stock) {
        if (stock != null && stock >= 0) {
            this.stock = stock;
            this.available = stock > 0;
        } else {
            this.stock = 0;
            this.available = false;
        }
    }

    public void setPrice(double price) {
        if (price < 0) {
            this.price = 0;
        } else {
            this.price = price;
        }
    }

}
