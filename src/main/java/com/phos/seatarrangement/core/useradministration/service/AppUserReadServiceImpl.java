package com.phos.seatarrangement.core.useradministration.service;

import com.phos.seatarrangement.core.exception.PlatformDataIntegrityException;
import com.phos.seatarrangement.core.security.domain.SecurityUser;
import com.phos.seatarrangement.core.security.service.JwtTokenService;
import com.phos.seatarrangement.core.useradministration.data.AppUserData;
import com.phos.seatarrangement.core.useradministration.data.TokenRequestData;
import com.phos.seatarrangement.core.useradministration.domain.AppUser;
import com.phos.seatarrangement.core.useradministration.exception.UserNotFoundException;
import com.phos.seatarrangement.core.useradministration.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserReadServiceImpl implements AppUserReadService{

    private final Logger logger = LoggerFactory.getLogger(AppUserReadServiceImpl.class);

    private final JwtTokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AppUserReadServiceImpl(JwtTokenService tokenService, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity fetchUser(Long appUserId) {
        logger.info("fetching user with id {}", appUserId);
        Optional<AppUser> appUser = userRepository.findById(appUserId);

        if(appUser.isPresent()){
            AppUserData appUserData = mapUserToData(appUser.get());
            return ResponseEntity.ok()
                    .body(Map.of("status", "Success", "data", appUserData));
        }
        throw new UserNotFoundException("error.user.not.found",
                String.format("user with id %d not found", appUserId));
    }

    private AppUserData mapUserToData(AppUser appUser) {
        logger.info("Mapping user to data...");
        return AppUserData.build(appUser.getUsername(), appUser.getFirstname(), appUser.getLastname(), appUser.getEmail());
    }

    @Override
    public ResponseEntity<Collection<AppUserData>> fetchAllUsers() {
        logger.info("Fetching all users...");
        List<AppUser> users = userRepository.findAll();

        List<AppUserData> userDatas = users.stream()
                .map(this::mapUserToData)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(userDatas);
    }

    @Override
    public ResponseEntity getToken(TokenRequestData data) {
        logger.info("generating token...");
        validateLoginRequest(data);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));
        String token = tokenService.generateToken(authentication);

        return ResponseEntity.ok()
                .body(Map.of("status", "Success","token",token));
    }

    @Override
    public ResponseEntity<Boolean> confirmUsername(String username) {
        logger.info("confirming username...");
        Optional<AppUser> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return ResponseEntity.ok()
                    .body(true);
        }
        return ResponseEntity.ok()
                .body(false);
    }

    private void validateLoginRequest(TokenRequestData data) {
        logger.info("Validating login request...");
        if(data == null){
            throw new PlatformDataIntegrityException("error.username.and.password.blank", "username and password must not be blank");
        }

        if(data.getUsername() == null || data.getUsername().isEmpty()){
            throw new PlatformDataIntegrityException("error.username.blank","username must not be blank");
        }

        if(data.getPassword() == null || data.getPassword().isEmpty()){
            throw new PlatformDataIntegrityException("error.password.blank","password must not be blank");
        }
    }

    @Override
    public AppUser getCurrentUser(){
        logger.info("fetching current logged in user...");
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if(principal instanceof UserDetails){
            String username = ((SecurityUser) principal).getUsername();
            Optional<AppUser> appUser = userRepository.findByUsername(username);

            if(appUser.isPresent()){
                return appUser.get();
            }
        }

        return null;
    }
}
