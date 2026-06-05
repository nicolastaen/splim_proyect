package com.duoc.splim.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * utilidad para generar parsear y validar los tokens JWT
 * 
 * JWT tiene tres partes separadas por puntos:
 *  Header.Payload.Signature
 * 
 * -Header      : algoritmo de firma (HS256)
 * -Payload     : claims → subjets (username), role, iat (emitido), exp (vence). 
 * -Signature   : HMAC-SHA256 firmado con la clave secreta del servidor
*/

@Component
public class JwtUtil {

    private final SecretKey key;

    /*duracion del token: 24 horas formato:(milisegundos) */
    private static final long EXPIRATION_MS = 86_400_000L;

    public JwtUtil(@Value("${jwt.secret}") String secret){
        // la clave debe tener al menos 32 caracteres (256 bits) para HMAC-SHA256
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    /* genera un token JWT firmado con el username y el rol del usuario */
    public String generateToken(String username, String role){
        return Jwts.builder()
            .subject(username)
            .claim("role", role)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
            .signWith(key)
            .compact();
    }

    /*esta funcion extrae el username ( SUBJET ) del token */
    public String extractUsername(String token){
        return parseClaims(token).getSubject();
    }

    /*esta funcion extrae el rol almacenado en el claim "role" */
    public String extraxtRole(String token){
        return parseClaims(token).get("role", String.class);
    }

    /**
     * aqui se valida que el token sea autentico y no haya expirado
     * si la firma no coincide con el token o vencio, lanza una excepcion interna
     */
    public boolean validateToken(String token){
        try{
            parseClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims parseClaims(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
