package com.diogo.microservices.services.implementations.usuario;


import com.diogo.microservices.model.usuario.RegraDeUsuario;
import com.diogo.microservices.model.usuario.Usuario;
import com.diogo.microservices.services.interfaces.usuario.CarregarUsuariosNaMemoria;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarregarUsuariosNaMemoriaImpl implements CarregarUsuariosNaMemoria {


    private final RedisTemplate<String, Object> redisTemplate;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> executar() {
        List<Usuario> usuarios = usuariosCadastrados();
        usuarios = gravarNaMemoria(usuarios);
        return usuarios;
    }




    protected List<Usuario> usuariosCadastrados()
    {
        List<Usuario> usuarios = List.of(
                new Usuario("admin", "Administrador", passwordEncoder.encode("admin"), List.of(RegraDeUsuario.ADMINISTRADOR)),
                new Usuario("user", "Usuário", passwordEncoder.encode("user"), List.of(RegraDeUsuario.USUARIO))
        );

        return usuarios;
    }

    protected List<Usuario> gravarNaMemoria (List<Usuario> usuarios)
    {
        for (Usuario usuario : usuarios) {
            try {
                String usuarioJson = new ObjectMapper().writeValueAsString(usuario);
                redisTemplate.opsForValue().set(usuario.getLogin(),usuarioJson);
            }catch (Exception ex)
            {
                throw new RuntimeException("Erro ao mapear usuario" + usuario.getLogin());
            }

        }
        return usuarios;
    }

}
