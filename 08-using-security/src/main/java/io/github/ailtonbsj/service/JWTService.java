package io.github.ailtonbsj.service;

import io.github.ailtonbsj.UsingJPAApplication;
import io.github.ailtonbsj.domain.entity.UserEntity;
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

@Service
public class JWTService {
    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.signaturekey}")
    private String signatureKey;

    public String generateToken(UserEntity user){
        long expirationStr = Long.valueOf(expiration);
        LocalDateTime expirationDatetime =
                LocalDateTime.now().plusMinutes(expirationStr);
        Instant instant = expirationDatetime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return Jwts.builder()
                .setSubject(user.getLogin())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, signatureKey)
                .compact();
    }

    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(UsingJPAApplication.class);
        JWTService service = context.getBean(JWTService.class);
        UserEntity user = UserEntity.builder().login("fulano").build();
        String token = service.generateToken(user);
        System.out.println(token);
    }

}
