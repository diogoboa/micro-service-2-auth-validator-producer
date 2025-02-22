package com.diogo.microservices.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequestMapping("api/imagem")
@AllArgsConstructor
@Tag(name = "Imagem", description = " - Recursos de imagens")
public class ImagemController {



    @PostMapping
    @SecurityRequirement(name = AUTHORIZATION)
    @Operation(summary = "Publicar imagem")
    public ResponseEntity<?> efetuarLogin(){



        System.out.println("passou aqui");

        /*UsuarioLogado usuarioLogado = logarUsuario.executar(command);

        return ResponseEntity.ok().body(usuarioLogado);*/

        return null;
    }




}
