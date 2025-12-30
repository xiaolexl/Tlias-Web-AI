package com.itxiaole;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    public void test(){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "aXR4aWFvbGU=")
                .addClaims(dataMap)
                .setExpiration(new Date( System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .compact();
        System.out.println(jwt);
    }

    public void testParseJWT(){
        String token = "";
        Claims claims = Jwts.parser()
                .setSigningKey("aXR4aWFvbGU=")
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);
    }
}
