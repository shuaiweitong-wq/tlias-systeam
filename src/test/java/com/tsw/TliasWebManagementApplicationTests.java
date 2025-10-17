package com.tsw;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "tsw");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "tsw".getBytes()) //签名算法
                .setClaims(claims) //自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置过期时间
                .compact();
        System.out.println(jwt);
    }
    @Test
    public void testParseJwt() {
        Claims claims = Jwts.parser()
                .setSigningKey("tsw".getBytes())
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzYwNjMwNzAyLCJ1c2VybmFtZSI6InRzdyJ9.9vJ2dnbc1OvkTHxVFc0FTWhTBPYoYJBM235Agw2VPF4")
                .getBody();
        System.out.println(claims);
    }

}
