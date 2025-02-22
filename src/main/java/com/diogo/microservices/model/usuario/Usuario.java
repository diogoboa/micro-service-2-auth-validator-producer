package com.diogo.microservices.model.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {


    private String login;
    private String nome;
    private String senhaCodificada;
    private List<RegraDeUsuario> regrasDeUsuario;

}
