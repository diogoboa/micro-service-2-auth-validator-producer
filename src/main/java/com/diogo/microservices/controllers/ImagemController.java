package com.diogo.microservices.controllers;


import com.diogo.microservices.model.imagem.Imagem;
import com.diogo.microservices.services.commands.in.CriarNovaImagemCommand;
import com.diogo.microservices.services.interfaces.imagem.CriarNovaImagem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("api/imagem")
@AllArgsConstructor
@Tag(name = "Imagem", description = " - Recursos de imagens")
public class ImagemController {

    private final CriarNovaImagem criarNovaImagem;

    @PostMapping
    @SecurityRequirement(name = AUTHORIZATION)
    @Operation(summary = "Publicar imagem")
    public ResponseEntity<?> inserirImagem(@RequestBody CriarNovaImagemCommand commmand){

        Imagem imagem = criarNovaImagem.executar(commmand);

        return ResponseEntity.accepted().body("Imagem publicada com sucesso");
    }




}
