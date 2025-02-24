package com.diogo.microservices.services.interfaces.imagem;

import com.diogo.microservices.model.imagem.Imagem;
import com.diogo.microservices.services.commands.in.CriarNovaImagemCommand;

public interface CriarNovaImagem {
    Imagem executar(CriarNovaImagemCommand command);
}
