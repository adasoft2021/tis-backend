package com.adasoft.tis.core.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

/**
 * Proveedor para la creación de tokens y desencriptación de ID de usuarios con JWT.
 */
@Component
public class JWTProvider {
    @Value("${jwt.secret}")
    private String secret;

    /**
     * Crear token de acuerdo al ID de un usuario.
     *
     * @param userId ID del usuario que se va a encriptar en el token.
     * @return el token creado.
     */
    public String create(final Long userId) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(String.valueOf(userId)).signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    /**
     * Desencriptar el ID del usuario de acuerdo a un token.
     *
     * @param token del que se va a desencriptar el ID del usuario
     * @return el ID del usuario desencriptado.
     */
    public Long decryptUserId(final String token) {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        Claims claims = Jwts.parser().setSigningKey(apiKeySecretBytes).parseClaimsJws(token).getBody();

        return Long.valueOf(claims.getId());
    }
}
