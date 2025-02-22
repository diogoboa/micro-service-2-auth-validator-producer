package com.diogo.microservices.services.implementations.imagem;

import com.diogo.microservices.services.commands.in.CriarNovaImagemCommand;
import com.diogo.microservices.services.interfaces.imagem.CriarNovaImagem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CriarNovaImagemImpl implements CriarNovaImagem {



    @Override
    public void executar(CriarNovaImagemCommand command) {
        validarEntradas(command);
        verificarFormato(command);

    }




    protected void validarEntradas(CriarNovaImagemCommand command) {
        if(command == null)
            throw new RuntimeException("Entrada vazia");
        if(command.base64Img() == null || command.base64Img().length() < 3)
            throw new RuntimeException("Base64 invalido");

    }


    protected void verificarFormato(CriarNovaImagemCommand command) {
        String base64 = command.base64Img();

        if (base64.startsWith("iVB")) { // PNG
            return;
        }
        if (base64.startsWith("/9j")) { // JPEG
            return;
        }
        if (base64.startsWith("Qk")) { // BMP
            return;
        }

        throw new RuntimeException("Formato de imagem inválido. Permitidos: PNG, JPEG, BMP.");
    }




}
