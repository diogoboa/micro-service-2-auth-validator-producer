package com.diogo.microservices.configurations.init;


import com.diogo.microservices.services.interfaces.usuario.CarregarUsuariosNaMemoria;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class InicializarUsuariosNaMemoria {

    private final CarregarUsuariosNaMemoria carregarUsuariosNaMemoria;

    @PostConstruct
    void iniciar()
    {
        carregarUsuariosNaMemoria.executar();
        System.out.println("Usuarios iniciados com sucesso!");
    }


}
