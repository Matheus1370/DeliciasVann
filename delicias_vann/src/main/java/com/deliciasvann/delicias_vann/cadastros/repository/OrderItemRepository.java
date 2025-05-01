package com.deliciasvann.delicias_vann.cadastros.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliciasvann.delicias_vann.cadastros.model.entity.OrderItemEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, UUID> {
    
}
