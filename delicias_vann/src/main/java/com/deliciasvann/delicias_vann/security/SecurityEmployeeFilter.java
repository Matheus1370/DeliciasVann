package com.deliciasvann.delicias_vann.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.deliciasvann.delicias_vann.providers.JWTEmployeeProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityEmployeeFilter extends OncePerRequestFilter {

    @Autowired
    private JWTEmployeeProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Only process /employee endpoints
        if (!request.getRequestURI().startsWith("/employee")) {
            filterChain.doFilter(request, response);
            return;
        }
        String header = request.getHeader("Authorization");
        if (header != null) {
            var token = this.jwtProvider.validateToken(header);
            if (token == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            var roles = token.getClaim("roles").asList(Object.class);
            var grants = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()))
                .toList();
            request.setAttribute("employee_id", token.getSubject());
            String email = token.getClaim("email").asString();
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null, grants);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

}
