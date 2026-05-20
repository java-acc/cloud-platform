package com.byc.common.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * Thin JJWT wrapper. Configure via:
 * <p>
 * app: security: jwt: secret: ...           # base64 or plain string, &gt;=32 bytes ttl-seconds: 86400
 */
@Component
public class JwtTokenProvider {
    
    private final SecretKey key;
    
    private final long ttlSeconds;
    
    public JwtTokenProvider(@Value("${app.security.jwt.secret:please-change-me-to-a-32-byte-secret!!}") String secret,
            @Value("${app.security.jwt.ttl-seconds:86400}") long ttlSeconds) {
        this.key = Keys.hmacShaKeyFor(padSecret(secret).getBytes(StandardCharsets.UTF_8));
        this.ttlSeconds = ttlSeconds;
    }
    
    private static String padSecret(String secret) {
        if (secret == null) {
            return "0".repeat(64);
        }
        return secret.length() < 32 ? secret + "0".repeat(32 - secret.length()) : secret;
    }
    
    public String issue(String subject, Map<String, Object> claims) {
        Date now = new Date();
        return Jwts.builder().subject(subject).claims(claims).issuedAt(now)
                .expiration(new Date(now.getTime() + ttlSeconds * 1000)).signWith(key).compact();
    }
    
    public Claims parse(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }
}
