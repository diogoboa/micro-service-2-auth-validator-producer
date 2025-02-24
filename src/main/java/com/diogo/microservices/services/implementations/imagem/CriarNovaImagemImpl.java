package com.diogo.microservices.services.implementations.imagem;

import com.diogo.microservices.dtos.InserirImagemNaFilaTabbitMQDTO;
import com.diogo.microservices.model.imagem.Imagem;
import com.diogo.microservices.services.commands.in.CriarNovaImagemCommand;
import com.diogo.microservices.services.interfaces.imagem.CriarNovaImagem;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.diogo.microservices.configurations.rabbitmq.RabbitMQEnvioDeImagensConfig.EXCHANGE_NAME;
import static com.diogo.microservices.configurations.rabbitmq.RabbitMQEnvioDeImagensConfig.ROUTING_KEY;


@Service
@AllArgsConstructor
public class CriarNovaImagemImpl implements CriarNovaImagem {

    private final RabbitTemplate rabbitTemplate;



    @Override
    public Imagem executar(CriarNovaImagemCommand command) {
        validarEntradas(command);
        verificarFormato(command);
        Imagem imagem = mapearParaImagem(command);
        String json = mapearParaFila(command);
        gravarImagemNafila(json);
        return imagem;
    }




    protected void validarEntradas(CriarNovaImagemCommand command) {
        if(command == null)
            throw new RuntimeException("Entrada vazia");
        if(command.base64Img() == null || command.base64Img().length() < 3)
            throw new RuntimeException("Base64 invalido");
        if(command.chaveUnica() == null || command.chaveUnica().isEmpty())
            throw new RuntimeException("Digite uma chave unica");

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

    private String mapearParaFila(CriarNovaImagemCommand command) {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            var dto = new InserirImagemNaFilaTabbitMQDTO(username, command.chaveUnica(), command.base64Img());
            return new ObjectMapper().writeValueAsString(dto);
        }catch (Exception ex)
        {
            throw new RuntimeException("Erro ao converter imagem");
        }

    }


    protected Imagem mapearParaImagem(CriarNovaImagemCommand command) {
        return new Imagem(null, command.chaveUnica(), command.base64Img());
    }


    protected void gravarImagemNafila(String json) {
        try {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, json);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar imagem para a fila: " + e.getMessage(), e);
        }
    }



}
