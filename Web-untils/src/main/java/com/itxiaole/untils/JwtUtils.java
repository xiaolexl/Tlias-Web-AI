package com.itxiaole.untils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 签名秘钥 (和测试类保持一致)
    private static String signKey = "aXR4aWFvbGU=";
    
    // 过期时间：12小时 (毫秒单位)
    private static Long expire = 43200000L; 

    /**
     * 生成JWT令牌
     * @param claims 传入的载荷数据 (例如: id, username等)
     * @return String 生成的JWT字符串
     */
    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, signKey) // 签名算法和秘钥
                .addClaims(claims) // 添加载荷
                .setExpiration(new Date(System.currentTimeMillis() + expire)) // 设置过期时间 (当前时间 + 12小时)
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT字符串
     * @return Claims 解析后的载荷数据
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey) // 指定解析的秘钥
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}