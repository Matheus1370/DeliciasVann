package com.deliciasvann.delicias_vann.cadastros.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "users")
public class UsersEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Email(message = "O campo e-mail deve conter um e-mail válido")
    @Column(nullable = false, unique = true)
    private String email;

    @Size(min = 10, max = 100, message = "A senha deve ter entre 10 e 100 caracteres")
    @Column(nullable = false)
    private String password; 

    private String role;

    @OneToOne(mappedBy = "user")
    private EmployeeEntity employee;

    @OneToOne(mappedBy = "user")
    private CompanyEntity company;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    public boolean isEmployee() {
        return this.employee != null;
    }

    public boolean isCompany() {
        return this.company != null;
    }

}
