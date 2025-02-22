package com.diogo.microservices.services.commands.out;

import com.diogo.microservices.model.usuario.RegraDeUsuario;

import java.time.LocalDateTime;
import java.util.List;

public record EfetuarLoginDeUsuarioCommandOut(

        String usuario,
        String token,
        LocalDateTime expiraEm,
        List<RegraDeUsuario> regrasDeUsuario

) {
}
