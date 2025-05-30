package com.deliciasvann.delicias_vann.cadastros.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliciasvann.delicias_vann.cadastros.model.dto.CustomerRequest;
import com.deliciasvann.delicias_vann.cadastros.model.dto.CustomerResponse;
import com.deliciasvann.delicias_vann.cadastros.model.entity.CustomerEntity;
import com.deliciasvann.delicias_vann.cadastros.repository.CustomerRepository;
import com.deliciasvann.delicias_vann.exceptions.UserFoundException;
import com.deliciasvann.delicias_vann.exceptions.UserNotFoundException;

@Service
public class CustomerService {
    
    @Autowired
    CustomerRepository repository;

    public UUID create(CustomerRequest request){
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserFoundException();
        }
        CustomerEntity entity = new CustomerEntity();
        BeanUtils.copyProperties(request, entity);
        return repository.save(entity).getId();
    }

    public UUID update(UUID id, CustomerRequest request) {
        CustomerEntity entity = repository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    
        BeanUtils.copyProperties(request, entity);
        return repository.save(entity).getId();
    }
    
    public CustomerResponse findById(UUID id){
        CustomerEntity entity = repository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
        CustomerResponse res = new CustomerResponse();
        BeanUtils.copyProperties(entity, res);
        return res;
    }

    public List<CustomerResponse> findAll(){
        List<CustomerEntity> entities = repository.findAll();
        List<CustomerResponse> responses = new ArrayList<>();
        for(CustomerEntity e: entities){
            CustomerResponse res = new CustomerResponse();
            BeanUtils.copyProperties(e, res);
            responses.add(res);
        }
        return responses;
    }

    public void delete(UUID id){
        repository.deleteById(id);
    }

}
