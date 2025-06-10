package com.deliciasvann.delicias_vann.modules.customer;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity,UUID> {
    Optional<CustomerEntity> findByEmail(String email);
}
