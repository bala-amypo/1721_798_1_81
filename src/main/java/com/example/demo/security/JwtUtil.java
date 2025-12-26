package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret:defaultSecretKeyForTestsOnly123456789012345}")
    private String secret;

    @Value("${jwt.expiration:86400}")
    private long expiration;

    /* =========================
       SIGNING KEY
       ========================= */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /* =========================
       GENERIC TOKEN
       ========================= */
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /* =========================
       USER TOKEN (TESTS)
       ========================= */
    public String generateTokenForUser(Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /* =========================
       USER TOKEN (CONTROLLER)
       ========================= */
    public String generateTokenForUser(User user) {
        return generateTokenForUser(
                user.getId(),
                user.getEmail(),
                user.getRole()   // ✅ FIX: role is already String
        );
    }

    /* =========================
       PARSING (jjwt 0.11.x)
       ========================= */
    public Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token);
    }

    /* =========================
       VALIDATION
       ========================= */
    public boolean validateToken(String token, String expectedEmail) {
        Claims claims = parseToken(token).getBody();
        return claims.getSubject().equals(expectedEmail)
                && claims.getExpiration().after(new Date());
    }

    /* =========================
       EXTRACTORS
       ========================= */
    public String extractUsername(String token) {
        return extractEmail(token);
    }

    public String extractEmail(String token) {
        return parseToken(token).getBody().getSubject();
    }

    public String extractRole(String token) {
        return parseToken(token).getBody().get("role", String.class);
    }

    public Long extractUserId(String token) {
        Object id = parseToken(token).getBody().get("userId");

        if (id instanceof Integer) {
            return ((Integer) id).longValue();
        }
        if (id instanceof Long) {
            return (Long) id;
        }
        return null;
    }
}
