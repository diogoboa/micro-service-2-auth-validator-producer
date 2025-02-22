package com.diogo.microservices.services.interfaces.imagem;

import com.diogo.microservices.services.commands.in.CriarNovaImagemCommand;

public interface CriarNovaImagem {
    void executar(CriarNovaImagemCommand command);
}
