package com.deliciasvann.delicias_vann.modules.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.deliciasvann.delicias_vann.exceptions.UserFoundException;
import com.deliciasvann.delicias_vann.modules.user.UsersEntity;
import com.deliciasvann.delicias_vann.modules.user.UsersRepository;
import com.deliciasvann.delicias_vann.modules.user.dto.UsersRequest;
import com.deliciasvann.delicias_vann.modules.user.dto.UsersResponse;

@Service
public class UsersService {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UsersEntity create(UsersRequest request) {
        repository.findByEmail(request.getEmail())
            .ifPresent((user) -> {
                throw new UserFoundException();
            });
        UsersEntity entity = new UsersEntity();
        BeanUtils.copyProperties(request, entity);
        var password = passwordEncoder.encode(request.getPassword());
        entity.setPassword(password);
        return repository.save(entity);
    }
    
    public UUID update(UUID id, UsersRequest request) {
        UsersEntity entity = repository.findById(id).orElseThrow();
        BeanUtils.copyProperties(request, entity);
        return repository.save(entity).getId();
    }
    
    public UsersResponse findById(UUID id) {
        UsersEntity entity = repository.findById(id).orElseThrow();
        return new UsersResponse(entity);
    }
    
    public List<UsersResponse> findAll() {
        List<UsersEntity> entities = repository.findAll();
        List<UsersResponse> responses = new ArrayList<>();
        for(UsersEntity e: entities){
            responses.add(new UsersResponse(e));
        }
        return responses;
    }
    
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
