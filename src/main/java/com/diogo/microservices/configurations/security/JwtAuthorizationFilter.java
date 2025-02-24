package com.diogo.microservices.configurations.security;


import com.diogo.microservices.configurations.exceptions.StandardError;
import com.diogo.microservices.services.interfaces.usuario.ChecarTokenDeUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;


@Component
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    @Autowired
    private ChecarTokenDeUsuario checarTokenDeUsuario;



    public JwtAuthorizationFilter(AuthenticationManager authenticationManager ) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request.getRequestURI().equals("/api/login") || !request.getRequestURI().matches("^/api.*")) {
            chain.doFilter(request, response);
        return;
        }

        if (request.getRequestURI().startsWith("/api/public/")) {
        chain.doFilter(request, response);
        return;
        }

        try {
            Authentication authentication = checarTokenDeUsuario.executar(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        }catch (Exception ex)
        {


        LocalDateTime dataHora = LocalDateTime.now();
        String erro = "Token inválido";
        String messageError = "Ocorreu um erro ao processar o token";
        String path = request.getRequestURI();
        StandardError error = new StandardError(dataHora, HttpStatus.FORBIDDEN.value(), erro, messageError, path);

        String errorJson = new ObjectMapper().writeValueAsString(error);

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.getWriter().write(errorJson);

        }

    }





}
