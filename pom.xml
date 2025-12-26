package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    // Must be >= 32 chars for HS256
    private static final String SECRET =
            "mySuperSecretKeyThatIsAtLeast32CharactersLong123";

    private static final long EXPIRATION_MS = 1000 * 60 * 60 * 24; // 1 day

    private final SecretKey key;

    public JwtUtil() {
        this.key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    /* =========================================================
       TOKEN GENERATION
       ========================================================= */

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key) // ✅ CORRECT FOR 0.12.x
                .compact();
    }

    public String generateTokenForUser(User user) {
        return Jwts.builder()
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key) // ✅ CORRECT
                .compact();
    }

    /* =========================================================
       TOKEN PARSING
       ========================================================= */

    public Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .verifyWith(key) // ✅ MUST be SecretKey
                .build()
                .parseSignedClaims(token); // ✅ REQUIRED for getPayload()
    }

    public String extractUsername(String token) {
        return parseToken(token).getPayload().getSubject();
    }

    public String extractRole(String token) {
        return (String) parseToken(token).getPayload().get("role");
    }

    public Long extractUserId(String token) {
        Object id = parseToken(token).getPayload().get("userId");
        return id == null ? null : Long.valueOf(id.toString());
    }

    public boolean isTokenValid(String token, String username) {
        try {
            String tokenUser = extractUsername(token);
            return tokenUser.equals(username);
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }
}
