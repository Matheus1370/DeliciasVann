package com.deliciasvann.delicias_vann.cadastros.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliciasvann.delicias_vann.cadastros.model.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity,UUID> {
    Optional<CustomerEntity> findByEmail(String email);
}
