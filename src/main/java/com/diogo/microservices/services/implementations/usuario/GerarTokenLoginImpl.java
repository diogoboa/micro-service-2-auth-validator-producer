package com.diogo.microservices.services.implementations.usuario;

import com.diogo.microservices.services.interfaces.usuario.GerarTokenLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GerarTokenLoginImpl implements GerarTokenLogin {

    @Value("${jwt.secret}")
    private String secretKey;



    @Override
    public String gerar(String username, Long expiraEmSegundos) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expiraEmSegundos);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return token;
    }
}
