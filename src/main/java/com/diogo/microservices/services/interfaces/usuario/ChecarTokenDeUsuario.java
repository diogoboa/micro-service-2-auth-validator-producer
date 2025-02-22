package com.diogo.microservices.services.interfaces.usuario;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface ChecarTokenDeUsuario {
    public Authentication executar(HttpServletRequest request);
}
