package com.diogo.microservices.services.implementations.usuario;

import com.diogo.microservices.model.usuario.Usuario;
import com.diogo.microservices.services.interfaces.usuario.ListarUsuarioPorUsername;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListarUsuarioPorUsernameImpl implements ListarUsuarioPorUsername {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;


    @Override
    public Usuario executar(String username) {

        try {
            String usuarioJson = redisTemplate.opsForValue().get(username);

            if(usuarioJson == null)
                throw new RuntimeException("Usuario inexistente");
            return objectMapper.readValue(usuarioJson, Usuario.class);


        }catch (JsonProcessingException ex)
        {
            throw new RuntimeException("Erro ao buscar usuario");
        }
    }


}

