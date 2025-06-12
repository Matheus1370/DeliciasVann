package com.deliciasvann.delicias_vann.modules.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.deliciasvann.delicias_vann.exceptions.UserFoundException;
import com.deliciasvann.delicias_vann.exceptions.UserNotFoundException;
import com.deliciasvann.delicias_vann.modules.customer.CustomerEntity;
import com.deliciasvann.delicias_vann.modules.customer.CustomerRepository;
import com.deliciasvann.delicias_vann.modules.customer.dto.CustomerRequest;
import com.deliciasvann.delicias_vann.modules.customer.dto.CustomerResponse;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UUID create(CustomerRequest request){
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserFoundException();
        }
        CustomerEntity entity = new CustomerEntity();
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        var password = passwordEncoder.encode(request.getPassword());
        entity.setPassword(password);
        return repository.save(entity).getId();
    }

    public UUID update(UUID id, CustomerRequest request) {
        CustomerEntity entity = repository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID " + id.toString()));
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        var password = passwordEncoder.encode(request.getPassword());
        entity.setPassword(password);
        return repository.save(entity).getId();
    }
    
    public CustomerResponse findById(UUID id){
        CustomerEntity entity = repository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID " + id.toString()));
        CustomerResponse res = new CustomerResponse();
        res.setId(entity.getId());
        res.setName(entity.getName());
        res.setEmail(entity.getEmail());
        res.setCreatedAt(entity.getCreatedAt());
        return res;
    }

    public List<CustomerResponse> findAll(){
        List<CustomerEntity> entities = repository.findAll();
        List<CustomerResponse> responses = new ArrayList<>();
        for(CustomerEntity e: entities){
            CustomerResponse res = new CustomerResponse();
            res.setId(e.getId());
            res.setName(e.getName());
            res.setEmail(e.getEmail());
            res.setCreatedAt(e.getCreatedAt());
            responses.add(res);
        }
        return responses;
    }

    public void delete(UUID id){
        repository.deleteById(id);
    }

}
