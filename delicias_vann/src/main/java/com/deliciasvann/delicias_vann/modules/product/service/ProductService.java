package com.deliciasvann.delicias_vann.modules.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliciasvann.delicias_vann.exceptions.UserNotFoundException;
import com.deliciasvann.delicias_vann.modules.product.ProductEntity;
import com.deliciasvann.delicias_vann.modules.product.ProductRepository;
import com.deliciasvann.delicias_vann.modules.product.dto.ProductRequest;
import com.deliciasvann.delicias_vann.modules.product.dto.ProductResponse;

@Service
public class ProductService {
    
    @Autowired
    ProductRepository repository;

    public ProductResponse create(ProductRequest request){
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(request, entity);
        ProductEntity saved = repository.save(entity);
        return new ProductResponse(saved);
    }

    public UUID update(UUID id, ProductRequest request) {
        ProductEntity entity = repository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException("Product not found with ID " + id.toString()));
    
        BeanUtils.copyProperties(request, entity);
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
