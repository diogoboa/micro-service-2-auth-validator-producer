package com.diogo.microservices.services.interfaces.usuario;

public interface GerarTokenLogin {
    String gerar(String username,  Long expiraEmSegundos);
}
