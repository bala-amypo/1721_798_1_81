package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET = "secretkey123456";
    private final long EXPIRATION = 1000 * 60 * 60;

    public String generateTokenForUser(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole())
                .claim("userId", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return (String) getClaims(token).get("role");
    }

    public Long extractUserId(String token) {
        return ((Number) getClaims(token).get("userId")).longValue();
    }

    public boolean isTokenValid(String token, String email) {
        return extractUsername(token).equals(email);
    }

    public Jws<Claims> parseToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
    }

    private Claims getClaims(String token) {
        return parseToken(token).getBody();
    }
}
