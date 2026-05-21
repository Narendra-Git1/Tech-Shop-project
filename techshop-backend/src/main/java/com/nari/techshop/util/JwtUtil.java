package com.nari.techshop.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private static final String SECRET_KEY =
            "mysecretkeymysecretkeymysecretkey";

    private static final long EXPIRATION_TIME =
            1000 * 60 * 60;

    private Key getSigningKey() {

        return new SecretKeySpec(
                SECRET_KEY.getBytes(),
                SignatureAlgorithm.HS256.getJcaName()
        );
    }

    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + EXPIRATION_TIME
                        )
                )
                .signWith(getSigningKey(),
                          SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {

        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String token,
                                 String email) {

        String extractedEmail =
                extractEmail(token);

        return extractedEmail.equals(email)
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {

        return extractClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims extractClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}