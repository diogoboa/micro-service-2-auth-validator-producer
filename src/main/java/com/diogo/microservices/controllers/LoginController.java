package com.diogo.microservices.controllers;

import com.diogo.microservices.services.commands.in.EfetuarLoginDeUsuarioCommandIn;
import com.diogo.microservices.services.commands.out.EfetuarLoginDeUsuarioCommandOut;
import com.diogo.microservices.services.interfaces.usuario.EfetuarLogin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequestMapping("api/login")
@AllArgsConstructor
@Tag(name = "Login", description = " - Recursos de autenticação")
public class LoginController {

    private final EfetuarLogin efetuarLogin;

    @PostMapping
    @Operation(summary = "Autenticar usuario")
    public ResponseEntity<EfetuarLoginDeUsuarioCommandOut> efetuarLogin(@RequestBody @Valid EfetuarLoginDeUsuarioCommandIn command){

        EfetuarLoginDeUsuarioCommandOut response = efetuarLogin.executar(command);

        return ResponseEntity.ok().body(response);

    }


}
