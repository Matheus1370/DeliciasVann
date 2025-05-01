package com.deliciasvann.delicias_vann.cadastros.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliciasvann.delicias_vann.cadastros.model.entity.UsersEntity;

public interface UsersRepository extends JpaRepository<UsersEntity, UUID> {
    Optional<UsersEntity> findByEmail(String email);
}
