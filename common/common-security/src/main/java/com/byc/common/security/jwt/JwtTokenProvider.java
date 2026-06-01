package com.byc.common.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * JWT token provider using JJWT library for token generation and validation.
 *
 * <p>Configured via application properties:
 * <ul>
 *   <li>{@code app.security.jwt.secret} - the signing secret (base64 or plain string, >=32 bytes)</li>
 *   <li>{@code app.security.jwt.ttl-seconds} - token time-to-live in seconds (default: 86400)</li>
 * </ul>
 */
@Component
public class JwtTokenProvider {

  private final SecretKey key;

  private final long ttlSeconds;

  /**
   * Constructs a new JwtTokenProvider with configured secret and TTL.
   *
   * @param secret the signing secret for JWT tokens (will be padded if less than 32 bytes)
   * @param ttlSeconds the token validity duration in seconds
   */
  public JwtTokenProvider(@Value("${app.security.jwt.secret:please-change-me-to-a-32-byte-secret!!}") String secret,
      @Value("${app.security.jwt.ttl-seconds:86400}") long ttlSeconds) {
    this.key = Keys.hmacShaKeyFor(padSecret(secret).getBytes(StandardCharsets.UTF_8));
    this.ttlSeconds = ttlSeconds;
  }

  /**
   * Pads the secret to meet minimum length requirements for HMAC-SHA key generation.
   *
   * @param secret the original secret string
   * @return a padded secret of at least 32 characters
   */
  private static String padSecret(String secret) {
    if (secret == null) {
      return "0".repeat(64);
    }
    return secret.length() < 32 ? secret + "0".repeat(32 - secret.length()) : secret;
  }

  /**
   * Issues a new JWT token with the specified subject and custom claims.
   *
   * @param subject the token subject (typically username or user ID)
   * @param claims additional claims to include in the token payload
   * @return a signed JWT token string
   */
  public String issue(String subject, Map<String, Object> claims) {
    Date now = new Date();
    return Jwts.builder().subject(subject).claims(claims).issuedAt(now)
        .expiration(new Date(now.getTime() + ttlSeconds * 1000)).signWith(key).compact();
  }

  /**
   * Parses and validates a JWT token, returning the claims payload.
   *
   * @param token the JWT token string to parse
   * @return the claims extracted from the validated token
   * @throws io.jsonwebtoken.JwtException if the token is invalid or expired
   */
  public Claims parse(String token) {
    return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
  }
}
