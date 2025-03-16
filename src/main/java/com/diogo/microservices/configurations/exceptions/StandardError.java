package com.diogo.microservices.configurations.exceptions;

import java.time.LocalDateTime;

public record StandardError(


        LocalDateTime timestamp,
        Integer status,
        String error,
        String message,
        String Path

) {
}
