package com.example.jwtwithredis.service.jwt;


import com.example.jwtwithredis.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.expiry}")
    private Integer expiry;

    @Value("${jwt.secret}")
    private String secret;

    private final RedisTemplate<String, String> redisTemplate;

    public String generateToken(UserEntity user) {
        Date iat = new Date();
        String token = Jwts.builder()
                .setSubject(user.getUsername().toString())
                .setIssuedAt(iat)
                .setExpiration(new Date(iat.getTime() + expiry))
                .addClaims(getAuthorities(user))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                .compact();

        String oldToken = redisTemplate.opsForValue().get(user.getUsername());
        System.out.println("Old Token = " + oldToken);
        if (oldToken != null) {
            redisTemplate.delete(oldToken);
        }
        System.out.println("New token = " + token);
        redisTemplate.opsForValue().set(user.getUsername(), token);

        return token;

    }

    public Jws<Claims> extractToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(token);
    }

    public Map<String, Object> getAuthorities(UserEntity user) {
        return Map.of("roles",
                user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList());
    }
}
