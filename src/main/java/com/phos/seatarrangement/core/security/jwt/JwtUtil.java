package com.phos.seatarrangement.core.security.jwt;

import com.phos.seatarrangement.core.useradministration.domain.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtUtil {

    @Value("${seats.jwt.secret.key}")
    private String secretKey;

    @Value("${seats.jwt.key.duration:86400000}")
    private Long jwtDuration;

    public String generateAccessToken(AppUser appUser){
        return Jwts.builder()
                .setSubject(String.format("%s,%s", appUser.getId(), appUser.getUsername()))
                .setIssuer("Phos")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtDuration))
                .signWith(SignatureAlgorithm.ES512, secretKey)
                .compact();
    }
}
