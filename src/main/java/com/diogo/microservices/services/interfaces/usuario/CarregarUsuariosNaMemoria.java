package com.diogo.microservices.services.interfaces.usuario;

import com.diogo.microservices.model.usuario.Usuario;

import java.util.List;


public interface CarregarUsuariosNaMemoria {

    List<Usuario> executar();

}
