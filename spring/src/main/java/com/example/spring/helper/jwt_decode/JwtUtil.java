package com.example.spring.helper.jwt_decode;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;

public class JwtUtil {
    static String SECRET = "codedidungso.me";
    static long JWT_EXPIRATION = 604800000L;
    public static String generateToken(String userId) {
        Date now = new Date();
        Date expiredTime = new Date(now.getTime() + JWT_EXPIRATION);
        String token = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(now)
                .setExpiration(expiredTime)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return token;
    }
    public static String verifyToken(String token){
        Claims data =  Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(token).getBody();
        // server : xac thuc token cilent gui co dung la do minh tao hay ko
        // docode body => lay ra du lieu nguoi dung
        return data.getSubject();
    }
}
