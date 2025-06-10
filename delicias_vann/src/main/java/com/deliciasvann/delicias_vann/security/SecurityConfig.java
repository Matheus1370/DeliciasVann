package com.deliciasvann.delicias_vann.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityCustomerFilter securityCustomerFilter;

    @Autowired
    private SecurityCompanyFilter securityCompanyFilter;

    @Autowired
    private SecurityEmployeeFilter securityEmployeeFilter;
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/customer/**").permitAll();
                auth.requestMatchers("/company/**").permitAll();
                auth.requestMatchers("/employee/**").permitAll();
                auth.requestMatchers("/product/**").permitAll();
                auth.requestMatchers("/order/**").permitAll();
                auth.requestMatchers("/order-item/**").permitAll();
                auth.requestMatchers("/order-item/**").permitAll();
                auth.requestMatchers("/payment/**").permitAll();
                auth.anyRequest().authenticated();
            })
            .addFilterBefore(securityCustomerFilter, BasicAuthenticationFilter.class)
            .addFilterBefore(securityCompanyFilter, BasicAuthenticationFilter.class)
            .addFilterBefore(securityEmployeeFilter, BasicAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
