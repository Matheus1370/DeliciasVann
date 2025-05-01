package com.deliciasvann.delicias_vann.cadastros.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliciasvann.delicias_vann.cadastros.model.dto.EmployeeRequest;
import com.deliciasvann.delicias_vann.cadastros.model.dto.EmployeeResponse;
import com.deliciasvann.delicias_vann.cadastros.model.dto.UsersRequest;
import com.deliciasvann.delicias_vann.cadastros.model.entity.EmployeeEntity;
import com.deliciasvann.delicias_vann.cadastros.model.entity.UsersEntity;
import com.deliciasvann.delicias_vann.cadastros.repository.EmployeeRepository;
import com.deliciasvann.delicias_vann.cadastros.repository.UsersRepository;
import com.deliciasvann.delicias_vann.exceptions.UserFoundException;
import com.deliciasvann.delicias_vann.exceptions.UserNotFoundException;

@Service
public class EmployeeService {
    
    @Autowired
    EmployeeRepository repository;
    
    @Autowired
    UsersRepository usersRepository;

    public UUID create(EmployeeRequest request, UsersRequest userRequest){
        if (usersRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new UserFoundException();
        }

        UsersEntity user = new UsersEntity();
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRole("EMPLOYEE");
        user = usersRepository.save(user);

        EmployeeEntity entity = new EmployeeEntity();
        BeanUtils.copyProperties(request, entity);
        return repository.save(entity).getId();
    }

    public UUID update(UUID id, EmployeeRequest request) {
        EmployeeEntity entity = repository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    
        BeanUtils.copyProperties(request, entity);
        return repository.save(entity).getId();
    }
    
    public EmployeeResponse findById(UUID id){
        EmployeeEntity entity = repository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
        return new EmployeeResponse(entity);
    }

    public List<EmployeeResponse> findAll(){
        List<EmployeeEntity> entities = repository.findAll();
        List<EmployeeResponse> responses = new ArrayList<>();
        for(EmployeeEntity e: entities){
            responses.add(new EmployeeResponse(e));
        }
        return responses;
    }

    public void delete(UUID id){
        repository.deleteById(id);
    }

}
