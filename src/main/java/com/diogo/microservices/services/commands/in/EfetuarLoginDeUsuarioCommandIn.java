package com.diogo.microservices.services.commands.in;

public record EfetuarLoginDeUsuarioCommandIn(
        String login,
        String senha
) {
}
