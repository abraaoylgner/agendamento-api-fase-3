package br.com.pos.tech.challenge.agendamento.Infrastructure.security;

import br.com.pos.tech.challenge.agendamento.Domain.entities.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret:chave_super_secreta_tech_challenge}")
    private String secret;

    private static final String ISSUER = "tech-challenge-app";

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(usuario.getEmail())
                    .withClaim("role", usuario.getTipoUsuario().name()) // Guardamos o perfil (MEDICO, ENFERMEIRO, PACIENTE)
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getSubject();
    }

    public String getRole(String token) {
        return JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getClaim("role").asString();
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}