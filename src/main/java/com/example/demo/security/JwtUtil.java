package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    /* =========================
       DEFAULTS (IMPORTANT)
       ========================= */

    // MUST NOT be null even without Spring
    private final String secret =
            "mySuperSecretKeyThatIsAtLeast32CharactersLong123";

    // 1 day
    private final long expirationSeconds = 86400;

    /* =========================
       INTERNAL HELPERS
       ========================= */

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    private Date getExpirationDate() {
        return new Date(System.currentTimeMillis() + expirationSeconds * 1000);
    }

    /* =========================
       TOKEN GENERATION
       ========================= */

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(getExpirationDate())
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateTokenForUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());

        return generateToken(claims, user.getEmail());
    }

    /* =========================
       TOKEN PARSING (TEST EXPECTS THIS)
       ========================= */

    public Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseClaimsJws(token);
    }

    /* =========================
       CLAIM EXTRACTION
       ========================= */

    public String extractUsername(String token) {
        return parseToken(token).getPayload().getSubject();
    }

    public String extractRole(String token) {
        return parseToken(token).getPayload().get("role", String.class);
    }

    public Long extractUserId(String token) {
        Object id = parseToken(token).getPayload().get("userId");
        if (id instanceof Integer) {
            return ((Integer) id).longValue();
        }
        return (Long) id;
    }

    /* =========================
       VALIDATION
       ========================= */

    public boolean isTokenValid(String token, String expectedUsername) {
        try {
            Claims claims = parseToken(token).getPayload();
            return claims.getSubject().equals(expectedUsername)
                    && claims.getExpiration().after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
