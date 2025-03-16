package com.diogo.microservices.services.commands.in;

public record CriarNovaImagemCommand(

        String chave,
        String base64Img

) {
}
