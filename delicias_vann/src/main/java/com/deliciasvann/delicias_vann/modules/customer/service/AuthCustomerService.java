package com.deliciasvann.delicias_vann.modules.customer.service;

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
import com.deliciasvann.delicias_vann.modules.customer.CustomerRepository;
import com.deliciasvann.delicias_vann.modules.customer.dto.AuthCustomerRequest;
import com.deliciasvann.delicias_vann.modules.customer.dto.AuthCustomerResponse;


@Service
public class AuthCustomerService {
    
    @Value("${security.token.secret.customer}")
    private String secretKey;

    @Autowired
    private CustomerRepository repository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCustomerResponse execute(AuthCustomerRequest request) throws AuthenticationException{
        var customer = this.repository.findByEmail(request.getEmail()).orElseThrow(
            () -> {
                throw new UserNotFoundException("Username/password incorret");
            }
        );

        var passwordMatches = this.passwordEncoder.matches(request.getPassword(), customer.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(1));
        var token = JWT.create()
            .withIssuer("delicias_vann")
            .withSubject(customer.getId().toString())
            .withExpiresAt(expiresIn)
            .withClaim("roles", Arrays.asList("CUSTOMER"))
            .sign(algorithm);

        var authCustomerResponse = AuthCustomerResponse.builder()
            .access_token(token)
            .expires_in(expiresIn.toEpochMilli())
            .build();

        return authCustomerResponse;
    }

}
