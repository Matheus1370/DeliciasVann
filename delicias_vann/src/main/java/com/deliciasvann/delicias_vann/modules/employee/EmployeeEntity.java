package com.deliciasvann.delicias_vann.modules.employee;

import java.util.UUID;

import com.deliciasvann.delicias_vann.modules.company.CompanyEntity;
import com.deliciasvann.delicias_vann.modules.user.UsersEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "employee")
public class EmployeeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Formato de telefone inválido. Exemplo: (11) 91234-5678")
    @Column(nullable = false)
    private String phone;
    
    @NotBlank(message = "O endereço é obrigatório")
    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;
    
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private UsersEntity user;
    
}
