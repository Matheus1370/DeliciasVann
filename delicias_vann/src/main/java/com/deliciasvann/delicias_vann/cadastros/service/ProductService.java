package com.deliciasvann.delicias_vann.cadastros.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliciasvann.delicias_vann.cadastros.model.dto.ProductRequest;
import com.deliciasvann.delicias_vann.cadastros.model.dto.ProductResponse;
import com.deliciasvann.delicias_vann.cadastros.model.entity.ProductEntity;
import com.deliciasvann.delicias_vann.cadastros.repository.ProductRepository;
import com.deliciasvann.delicias_vann.exceptions.UserNotFoundException;

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
            .orElseThrow(() -> new UserNotFoundException(id));
    
        BeanUtils.copyProperties(request, entity);
        return repository.save(entity).getId();
    }
    
    public ProductResponse findById(UUID id){
        ProductEntity entity = repository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
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
