package com.example.jet.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    private static final String SECRET_STRING = "YourVeryLongAndSecureSecretKeyForHS256MustBeAtLeast32BytesLong";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(UUID userId, String username) {
        return Jwts.builder()
                .subject(username)
                .claim("userId", userId.toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SECRET_KEY)
                .compact();
    }
}