package com.example.spring_jscode.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TokenUtils {

    public static String createToken(String username, String secretKey, Long expiredMilliSecond) {
        Claims claims = Jwts.claims();
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMilliSecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
