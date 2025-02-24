package com.diogo.microservices.services.commands.in;

public record CriarNovaImagemCommand(

        String chaveUnica,
        String base64Img

) {
}
