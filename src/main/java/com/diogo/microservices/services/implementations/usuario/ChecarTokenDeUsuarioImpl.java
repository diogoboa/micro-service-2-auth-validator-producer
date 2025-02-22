package com.diogo.microservices.services.implementations.usuario;


import com.diogo.microservices.model.usuario.Usuario;
import com.diogo.microservices.services.interfaces.usuario.ChecarTokenDeUsuario;
import com.diogo.microservices.services.interfaces.usuario.ListarUsuarioPorUsername;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;




@Service
@Transactional
public class ChecarTokenDeUsuarioImpl implements ChecarTokenDeUsuario {


    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration_time}")
    private long expirationTime;

    @Autowired
    private ListarUsuarioPorUsername listarUsuarioPorUsername;

    @Override
    public Authentication executar(HttpServletRequest request) {

        try
        {
            String token = request.getHeader("Authorization");
            if(token == null)
                throw new RuntimeException("o token não pode ser nulo");

            if(!token.toUpperCase().startsWith("BEARER "))
                token = "Bearer "+ token.trim();


            token = token.substring(7);


            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            Usuario usuario = listarUsuarioPorUsername.executar(username);


            List<GrantedAuthority> listaDeRegras = usuario.getRegrasDeUsuario().stream()
                    .map(regra -> new SimpleGrantedAuthority(regra.name()))
                    .collect(Collectors.toList());

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, new Object(),  listaDeRegras);


            return authenticationToken;


        }catch (Exception exception)
        {
            throw new RuntimeException("Token invalido!");
        }


       /* UserDetails user = listarUsuarioPorUsername.executar(username);




        return authenticationToken;

        */




    }
}
