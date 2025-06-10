package com.deliciasvann.delicias_vann.modules.employee.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.deliciasvann.delicias_vann.exceptions.UserNotFoundException;
import com.deliciasvann.delicias_vann.modules.employee.dto.AuthEmployeeRequest;
import com.deliciasvann.delicias_vann.modules.employee.dto.AuthEmployeeResponse;
import com.deliciasvann.delicias_vann.modules.user.UsersRepository;

@Service
public class AuthEmployeeService {
    
    @Value("${security.token.secret.employee}")
    private String secretKey;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthEmployeeResponse execute(AuthEmployeeRequest request) throws AuthenticationException{
        var users = usersRepository.findByEmail(request.getEmail()).orElseThrow(
            () -> {
                throw new UserNotFoundException("Email/password incorrect");
            }
        );

        var passwordMatches = passwordEncoder.matches(request.getPassword(), users.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }
        
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(1));
        var token = JWT.create()
            .withIssuer("delicias_vann")
            .withSubject(users.getEmployee().getId().toString())
            .withExpiresAt(expiresIn)
            .withClaim("email", users.getEmail())
            .withClaim("roles", Arrays.asList("EMPLOYEE"))
            .sign(algorithm);

        var authEmployeeResponse = AuthEmployeeResponse.builder()
            .access_token(token)
            .expires_in(expiresIn.toEpochMilli())
            .build();

        return authEmployeeResponse;

    }

}
