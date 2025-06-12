package com.deliciasvann.delicias_vann.modules.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliciasvann.delicias_vann.exceptions.UserFoundException;
import com.deliciasvann.delicias_vann.exceptions.UserNotFoundException;
import com.deliciasvann.delicias_vann.modules.company.CompanyRepository;
import com.deliciasvann.delicias_vann.modules.employee.EmployeeEntity;
import com.deliciasvann.delicias_vann.modules.employee.EmployeeRepository;
import com.deliciasvann.delicias_vann.modules.employee.dto.EmployeeRequest;
import com.deliciasvann.delicias_vann.modules.employee.dto.EmployeeResponse;
import com.deliciasvann.delicias_vann.modules.employee.dto.EmployeeUserRequest;
import com.deliciasvann.delicias_vann.modules.user.UsersEntity;
import com.deliciasvann.delicias_vann.modules.user.UsersRepository;

@Service
public class EmployeeService {
    
    @Autowired
    EmployeeRepository repository;
    
    @Autowired
    UsersRepository usersRepository;
    
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public EmployeeResponse create(EmployeeUserRequest request){
        if (usersRepository.findByEmail(request.getUsersRequest().getEmail()).isPresent()) {
            throw new UserFoundException();
        }

        var company = companyRepository.findById(request.getEmployeeRequest().getCompanyId()).orElseThrow(
            () -> {
                throw new UserNotFoundException("Company not exist");
            }
        );

        UsersEntity user = new UsersEntity();
        user.setEmail(request.getUsersRequest().getEmail());
        user.setPassword(passwordEncoder.encode(request.getUsersRequest().getPassword()));
        user.setRole("EMPLOYEE");
        user = usersRepository.save(user);

        EmployeeEntity employee = new EmployeeEntity();
        employee.setName(request.getEmployeeRequest().getName());
        employee.setPhone(request.getEmployeeRequest().getPhone());
        employee.setAddress(request.getEmployeeRequest().getAddress());
        employee.setUser(user);
        employee.setCompany(company);

        EmployeeEntity saved = repository.save(employee);
        return new EmployeeResponse(saved);
    }

    public UUID update(UUID id, EmployeeRequest request) {
        EmployeeEntity entity = repository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID " + id.toString()));
        // Manual field assignment
        entity.setName(request.getName());
        entity.setAddress(request.getAddress());
        entity.setPhone(request.getPhone());
        // If companyId is present, update company
        if (request.getCompanyId() != null) {
            // You may want to validate the company exists here
            // entity.setCompany(...)
        }
        return repository.save(entity).getId();
    }
    
    public EmployeeResponse findById(UUID id){
        EmployeeEntity entity = repository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID " + id.toString()));
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
