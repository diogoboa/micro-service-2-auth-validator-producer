package com.diogo.microservices.services.commands.in;

public record CriarNovaImagemCommand(

        String apelido,
        String base64Img

) {
}
