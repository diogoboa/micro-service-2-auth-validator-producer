package com.diogo.microservices.configurations.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                    .title("Testes de MicroServices")
                    .version("v1")
                    .description("Api desenvolvida para testes")
                    .contact(new Contact()
                            .name("Diogo Boaventura")
                            .url("https://www.linkedin.com/in/diogoboa/")
                            .email("diogo.boaventura.ufms@gmail.com"))
                    .license(new License()
                            .name("Publico")
                            .url("Publico"))
                    .termsOfService("Publico")
            )
                //.addServersItem(new Server().url("http://localhost"))
                //.addServersItem(new Server().url("https://localhost"))
                .components(new Components().addSecuritySchemes("Authorization",
                        new SecurityScheme()
                                .name("authorization")
                                .type(SecurityScheme.Type.APIKEY)
                                .scheme("HTTP")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .description("Entre com a JWT Token (Bearer)")
                ));
    }

}
