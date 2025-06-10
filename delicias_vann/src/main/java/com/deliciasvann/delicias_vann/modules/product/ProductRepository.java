package com.deliciasvann.delicias_vann.modules.product;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,UUID>{
    
}
