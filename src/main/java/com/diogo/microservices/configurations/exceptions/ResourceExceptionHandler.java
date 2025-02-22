package com.diogo.microservices.configurations.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


@Slf4j
@ControllerAdvice
public class ResourceExceptionHandler {



    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> execptionAll(Exception e, HttpServletRequest request){



        LocalDateTime dataHora = LocalDateTime.now();
        String erro = "ERRO";
        int httpValue = HttpStatus.FORBIDDEN.value();
        String messageError = e == null ? "Erro desconhecido" : e.getLocalizedMessage();
        String path = request.getRequestURI();
        StandardError error = new StandardError(dataHora, httpValue, erro, messageError, path);




        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }








}
