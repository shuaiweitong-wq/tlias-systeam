package com.tsw.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {
    private static String secret = "tlias";
    private static long expire = 60 * 60 * 1000;

    public static String generateToken(Map<String, Object>  claims) {
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret.getBytes())
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }
    public static Claims parseToken(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
