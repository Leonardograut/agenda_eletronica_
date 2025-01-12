package com.projeto.jwt.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.projeto.jwt.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;


@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
         return JWT.create()
                 .withIssuer("api-jwt")
                 .withSubject(usuario.getEmail())
                 .withClaim("id",usuario.getId())
                 .withExpiresAt(LocalDateTime.now()
                         .plusMinutes(10).toInstant(ZoneOffset.of("-03:00")))
                 .sign(Algorithm.HMAC256(secret));
    }

    public Optional<String> validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return Optional.of(JWT.require(algorithm)
                    .withIssuer("api-jwt")
                    .build()
                    .verify(token)
                    .getSubject());
        }catch (JWTVerificationException e){
            return  Optional.empty();
        }
    }


}
