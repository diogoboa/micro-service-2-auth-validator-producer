package com.diogo.microservices.controllers;


import com.diogo.microservices.model.imagem.Imagem;
import com.diogo.microservices.services.commands.in.CriarNovaImagemCommand;
import com.diogo.microservices.services.interfaces.imagem.CriarNovaImagem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("api/imagens")
@AllArgsConstructor
@Tag(name = "Imagem", description = " - Recursos de imagens")
@SecurityRequirement(name = AUTHORIZATION)
public class ImagemController {

    private final CriarNovaImagem criarNovaImagem;

    @PostMapping
    @Operation(summary = "Publicar imagem")
    public ResponseEntity<?> inserirImagem(@RequestBody CriarNovaImagemCommand commmand){

        Imagem imagem = criarNovaImagem.executar(commmand);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(imagem);
    }




}
