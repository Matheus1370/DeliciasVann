package com.deliciasvann.delicias_vann.cadastros.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliciasvann.delicias_vann.cadastros.model.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {
    
}
