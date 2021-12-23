package com.example.spring.helper.jwt_decode;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    static String SECRET = "codedidungso.me";
    static long JWT_EXPIRATION = 604800000L;

    public static String generateToken(String userId, String role) {
        Date now = new Date();
        Date expiredTime = new Date(now.getTime() + JWT_EXPIRATION);
        Map map = new HashMap<>();
        map.put("userId", userId);
        map.put("role", role);
//        map.put("password")
        String token = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setClaims(map)
                .setIssuedAt(now)
                .setExpiration(expiredTime)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        System.out.println(token);
        return token;
    }

    public static Claims getAllClaimsFromToken(String token) {
        Claims claims = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token)
                        .getBody();
        return claims;
    }

    public static Claims verifyToken(String token) {
        Claims data = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(token).getBody();
        // server : xac thuc token cilent gui co dung la do minh tao hay ko
        // docode body => lay ra du lieu nguoi

        return data;
    }
}
