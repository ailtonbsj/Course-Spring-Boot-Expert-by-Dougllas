package io.github.ailtonbsj.service;

import io.github.ailtonbsj.UsingJPAApplication;
import io.github.ailtonbsj.domain.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JWTService {
    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.signaturekey}")
    private String signatureKey;

    public String generateToken(UserEntity user){
        long expirationStr = Long.parseLong(expiration);
        LocalDateTime expirationDatetime =
                LocalDateTime.now().plusMinutes(expirationStr);
        Instant instant = expirationDatetime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("email", "fulano@gmail.com");
        claims.put("roles", "admin");

        return Jwts.builder()
                .setSubject(user.getLogin())
                .setExpiration(date)
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS512, signatureKey)
                .compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(signatureKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValidToken(String token){
        try {
            Claims claims = getClaims(token);
            Date expiration = claims.getExpiration();
            LocalDateTime date = expiration.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            return !LocalDateTime.now().isAfter(date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String getLoginUser(String token) throws ExpiredJwtException {
        return (String) getClaims(token).getSubject();
    }

    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(UsingJPAApplication.class);
        JWTService service = context.getBean(JWTService.class);
        UserEntity user = UserEntity.builder().login("fulano").build();
        String token = service.generateToken(user);
        System.out.println(token);

        boolean validToken = service.isValidToken(token);
        System.out.println("Valid token: " + validToken);

        System.out.println(service.getLoginUser(token));
    }

}
