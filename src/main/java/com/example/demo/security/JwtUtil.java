package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class JwtUtil {

    private final String SECRET = "secretkey123456";

    public String generateTokenForUser(com.example.demo.entity.User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole())
                .claim("userId", user.getId())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return parseToken(token).getSubject();
    }

    public String extractRole(String token) {
        return (String) parseToken(token).get("role");
    }

    public Long extractUserId(String token) {
        return ((Number) parseToken(token).get("userId")).longValue();
    }

    public boolean isTokenValid(String token, String email) {
        return extractUsername(token).equals(email);
    }
}
