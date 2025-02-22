package com.diogo.microservices.services.implementations.usuario;

import com.diogo.microservices.model.usuario.Usuario;
import com.diogo.microservices.services.commands.in.EfetuarLoginDeUsuarioCommandIn;
import com.diogo.microservices.services.commands.out.EfetuarLoginDeUsuarioCommandOut;
import com.diogo.microservices.services.interfaces.usuario.EfetuarLogin;
import com.diogo.microservices.services.interfaces.usuario.GerarTokenLogin;
import com.diogo.microservices.services.interfaces.usuario.ListarUsuarioPorUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Service
public class EfetuarLoginImpl implements EfetuarLogin {

    @Value("${jwt.expiration_time}")
    private long expirationTime;

    @Autowired
    private ListarUsuarioPorUsername listarUsuarioPorUsername;

    @Autowired
    private GerarTokenLogin gerarTokenLogin;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public EfetuarLoginDeUsuarioCommandOut executar(EfetuarLoginDeUsuarioCommandIn command) {


        Usuario usuario = listarUsuarioPorUsername.executar(command.login());

        String senhaAtual = usuario.getSenhaCodificada();
        String senhaInformadaPeloUsuario = command.senha();



        if(passwordEncoder.matches(senhaInformadaPeloUsuario, senhaAtual))
        {
            String token = gerarTokenLogin.gerar(usuario.getLogin(), expirationTime);
            LocalDateTime expiraEm = LocalDateTime.now().plus(expirationTime, ChronoUnit.MILLIS);
            return new EfetuarLoginDeUsuarioCommandOut (usuario.getLogin(), token, expiraEm, usuario.getRegrasDeUsuario());
        }
        else {
            throw new RuntimeException("Senha inválida");
        }


    }

}
