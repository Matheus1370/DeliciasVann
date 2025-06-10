package com.deliciasvann.delicias_vann.modules.company.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliciasvann.delicias_vann.exceptions.UserNotFoundException;
import com.deliciasvann.delicias_vann.modules.company.CompanyEntity;
import com.deliciasvann.delicias_vann.modules.company.CompanyRepository;
import com.deliciasvann.delicias_vann.modules.company.dto.CompanyRequest;
import com.deliciasvann.delicias_vann.modules.company.dto.CompanyResponse;
import com.deliciasvann.delicias_vann.modules.company.dto.CompanyUserRequest;
import com.deliciasvann.delicias_vann.modules.user.UsersEntity;
import com.deliciasvann.delicias_vann.modules.user.service.UsersService;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UsersService userService;


    public CompanyResponse create(CompanyUserRequest dto){
        
        UsersEntity user = userService.create(dto.getUsersRequest());

        CompanyEntity company = new CompanyEntity();
        company.setName(dto.getCompanyRequest().getName());
        company.setPhone(dto.getCompanyRequest().getPhone());
        company.setAddress(dto.getCompanyRequest().getAddress());
        company.setUser(user);

        user.setCompany(company);

        CompanyEntity saved = companyRepository.save(company);

        return new CompanyResponse(saved);
    }

    public CompanyEntity update(UUID id, CompanyRequest request) {
        CompanyEntity entity = companyRepository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID " + id.toString()));
    
        BeanUtils.copyProperties(request, entity);
        return companyRepository.save(entity);
    }
    
    public CompanyResponse findById(UUID id){
        CompanyEntity entity = companyRepository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID " + id.toString()));
        return new CompanyResponse(entity);
    }

    public List<CompanyResponse> findAll(){
        List<CompanyEntity> entities = companyRepository.findAll();
        List<CompanyResponse> responses = new ArrayList<>();
        for(CompanyEntity e: entities){
            responses.add(new CompanyResponse(e));
        }
        return responses;
    }

    public void delete(UUID id){
        companyRepository.deleteById(id);
    }

}
