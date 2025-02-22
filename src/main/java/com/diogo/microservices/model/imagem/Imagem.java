package com.diogo.microservices.model.imagem;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Imagem {

    private UUID id;
    private String nome;
    private String base64Img;

}
