package com.diogo.microservices.commands.in;

public record EfetuarLoginDeUsuarioCommandIn(
        String login,
        String senha
) {
}
