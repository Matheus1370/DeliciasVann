package com.deliciasvann.delicias_vann.modules.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliciasvann.delicias_vann.exceptions.UserNotFoundException;
import com.deliciasvann.delicias_vann.modules.company.CompanyEntity;
import com.deliciasvann.delicias_vann.modules.company.CompanyRepository;
import com.deliciasvann.delicias_vann.modules.product.ProductEntity;
import com.deliciasvann.delicias_vann.modules.product.ProductRepository;
import com.deliciasvann.delicias_vann.modules.product.dto.ProductRequest;
import com.deliciasvann.delicias_vann.modules.product.dto.ProductResponse;

@Service
public class ProductService {
    
    @Autowired
    ProductRepository repository;

    @Autowired
    CompanyRepository companyRepository;

    public ProductResponse create(ProductRequest request){
        if (request.getCompany() == null) {
            throw new IllegalArgumentException("Empresa obrigatória para criar produto");
        }
        CompanyEntity company = companyRepository.findById(request.getCompany())
            .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada para o ID informado: " + request.getCompany()));
        ProductEntity entity = new ProductEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setCategory(request.getCategory());
        entity.setImageUrl(request.getImageUrl());
        entity.setCompany(company);
        entity.setPrice(request.getPrice() != null ? request.getPrice() : 0.0);
        entity.setStock(request.getStock() != null ? request.getStock() : 0);
        // available is set by setStock
        ProductEntity saved = repository.save(entity);
        return new ProductResponse(saved);
    }

    public UUID update(UUID id, ProductRequest request) {
        ProductEntity entity = repository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException("Product not found with ID " + id.toString()));
        // Manual field assignment
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setCategory(request.getCategory());
        entity.setImageUrl(request.getImageUrl());
        entity.setPrice(request.getPrice() != null ? request.getPrice() : 0.0);
        entity.setStock(request.getStock() != null ? request.getStock() : 0);
        // Optionally update company if request.getCompany() is present
        if (request.getCompany() != null) {
            CompanyEntity company = companyRepository.findById(request.getCompany())
                .orElse(null);
            if (company != null) {
                entity.setCompany(company);
            }
        }
        return repository.save(entity).getId();
    }
    
    public ProductResponse findById(UUID id){
        ProductEntity entity = repository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException("Product not found with ID " + id.toString()));
        return new ProductResponse(entity);
    }

    public List<ProductResponse> findAll(){
        List<ProductEntity> entities = repository.findAll();
        List<ProductResponse> responses = new ArrayList<>();
        for(ProductEntity e: entities){
            responses.add(new ProductResponse(e));
        }
        return responses;
    }

    public void delete(UUID id){
        repository.deleteById(id);
    }

}
