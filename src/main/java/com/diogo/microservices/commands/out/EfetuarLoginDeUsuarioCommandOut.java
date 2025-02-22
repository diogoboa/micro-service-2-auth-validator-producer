package com.diogo.microservices.commands.out;

import com.diogo.microservices.model.RegraDeUsuario;

import java.time.LocalDateTime;
import java.util.List;

public record EfetuarLoginDeUsuarioCommandOut(

        String usuario,
        String token,
        LocalDateTime expiraEm,
        List<RegraDeUsuario> regrasDeUsuarios

) {
}
