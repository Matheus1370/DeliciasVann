package com.deliciasvann.delicias_vann.modules.orders.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliciasvann.delicias_vann.modules.orders.entities.OrdersEntity;

public interface OrdersRepository extends JpaRepository<OrdersEntity, UUID> {
    
}
