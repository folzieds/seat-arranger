package com.phos.seatarrangement.core.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${seats.jwt.secret.key}")
    private String secretKey;

    @Value("${seats.jwt.key.duration}")
    private Long jwtDuration;

    public String getAccessToken(){
        return null;
    }
}
