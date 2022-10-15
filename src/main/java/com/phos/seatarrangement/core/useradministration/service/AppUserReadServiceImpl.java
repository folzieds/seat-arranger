package com.phos.seatarrangement.core.useradministration.service;

import com.phos.seatarrangement.core.security.service.JwtTokenService;
import com.phos.seatarrangement.core.useradministration.data.AppUserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class AppUserReadServiceImpl implements AppUserReadService{

    private final Logger logger = LoggerFactory.getLogger(AppUserReadServiceImpl.class);

    private final JwtTokenService tokenService;

    public AppUserReadServiceImpl(JwtTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public ResponseEntity<AppUserData> fetchUser(Long appUserId) {
        return null;
    }

    @Override
    public ResponseEntity<Collection<AppUserData>> fetchAllUsers() {
        return null;
    }

    @Override
    public ResponseEntity getToken(Authentication authentication) {
        String token = tokenService.generateToken(authentication);

        return ResponseEntity.ok()
                .body(Map.of("status", "Success","token",token));
    }
}
