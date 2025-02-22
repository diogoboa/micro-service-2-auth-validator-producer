package com.diogo.microservices.controllers;

import com.diogo.microservices.commands.in.EfetuarLoginDeUsuarioCommandIn;
import com.diogo.microservices.commands.out.EfetuarLoginDeUsuarioCommandOut;
import com.diogo.microservices.services.interfaces.EfetuarLogin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/login")
@AllArgsConstructor
@Tag(name = "Login (v1)", description = " - Recursos de autenticação")
public class LoginController {


    private final EfetuarLogin efetuarLogin;

    @PostMapping
    @Operation(summary = "Autenticar usuario")
    public ResponseEntity<EfetuarLoginDeUsuarioCommandOut> efetuarLogin(@RequestBody @Valid EfetuarLoginDeUsuarioCommandIn command){

        /*UsuarioLogado usuarioLogado = logarUsuario.executar(command);

        return ResponseEntity.ok().body(usuarioLogado);*/

        return null;
    }




}
