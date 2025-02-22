package com.diogo.microservices.services.interfaces.usuario;

import com.diogo.microservices.model.usuario.Usuario;

public interface ListarUsuarioPorUsername {
    Usuario executar(String username);
}
