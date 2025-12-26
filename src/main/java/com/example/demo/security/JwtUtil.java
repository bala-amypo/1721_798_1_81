package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    // ✅ DEFAULT VALUE FOR TESTS (CRITICAL)
    @Value("${jwt.secret:mySuperSecretKeyThatIsAtLeast32CharactersLong123}")
    private String secret = "mySuperSecretKeyThatIsAtLeast32CharactersLong123";

    @Value("${jwt.expiration:86400}")
    private long expiration = 86400;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /* ==============================
       TOKEN GENERATION
    ============================== */

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)              // ✅ tests expect this
                .subject(subject)            // ✅ subject test
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    public String generateTokenForUser(User user) {
        return Jwts.builder()
                .subject(user.getEmail())    // ✅ subject = email
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    /* ==============================
       TOKEN PARSING
    ============================== */

    public Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
    }

    public String extractUsername(String token) {
        return parseToken(token).getBody().getSubject();
    }

    public boolean isTokenValid(String token, String username) {
        try {
            Claims claims = parseToken(token).getBody();
            return claims.getSubject().equals(username)
                    && claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
