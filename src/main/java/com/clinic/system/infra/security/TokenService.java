package com.clinic.system.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.clinic.system.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            var alg = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API clinic.med")
                    .withSubject(user.getLogin())
                    .withExpiresAt(dataExpired())
                    .sign(alg);
        } catch (JWTCreationException exception){
            throw new RuntimeException("error when generate token jwt", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var alg = Algorithm.HMAC256(secret);
            return JWT.require(alg)
                    .withIssuer("API clinic.med")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT invalid ou expired!");
        }
    }

    private Instant dataExpired() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
