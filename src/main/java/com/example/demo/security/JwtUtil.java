// File: src/main/java/com/example/demo/security/JwtUtil.java
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

    // Default so TestNG works (Spring context not loaded)
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

    private Date expirationDate() {
        return new Date(System.currentTimeMillis() + expirationSeconds * 1000);
    }

    /* =========================
       TOKEN GENERATION
       ========================= */

    // Used by t60
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate())
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
       TOKEN PARSING (0.11.x)
       ========================= */

    public Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token);
    }

    /* =========================
       CLAIM EXTRACTION
       ========================= */

    public String extractUsername(String token) {
        return parseToken(token).getBody().getSubject();
    }

    public String extractRole(String token) {
        return (String) parseToken(token).getBody().get("role");
    }

    public Long extractUserId(String token) {
        Object id = parseToken(token).getBody().get("userId");
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
            String username = extractUsername(token);
            Date expiration = parseToken(token).getBody().getExpiration();
            return username.equals(expectedUsername)
                    && expiration.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
