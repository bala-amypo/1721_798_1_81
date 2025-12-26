package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // Default value REQUIRED for TestNG (no Spring context)
    @Value("${jwt.secret:mySuperSecretKeyThatIsAtLeast32CharactersLong123}")
    private String secret;

    @Value("${jwt.expiration:86400}")
    private long expirationSeconds;

    /* =========================
       INTERNAL
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

    // Used by t60
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(getExpirationDate())
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Used by AuthController + tests
    public String generateTokenForUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());

        return generateToken(claims, user.getEmail());
    }

    /* =========================
       TOKEN PARSING (0.12.x)
       ========================= */

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()                    // ✅ REQUIRED
                .parseClaimsJws(token)     // ✅ NOW CORRECT
                .getBody();
    }

    /* =========================
       CLAIM EXTRACTION
       ========================= */

    public String extractUsername(String token) {
        return getAllClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return getAllClaims(token).get("role", String.class);
    }

    public Long extractUserId(String token) {
        Object id = getAllClaims(token).get("userId");
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
            Claims claims = getAllClaims(token);
            return claims.getSubject().equals(expectedUsername)
                    && claims.getExpiration().after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
