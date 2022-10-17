package com.phos.seatarrangement.core.useradministration.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phos.seatarrangement.core.exception.PlatformDataIntegrityException;
import com.phos.seatarrangement.core.security.service.JwtTokenService;
import com.phos.seatarrangement.core.useradministration.data.AppUserData;
import com.phos.seatarrangement.core.useradministration.data.TokenRequestData;
import com.phos.seatarrangement.core.useradministration.domain.AppUser;
import com.phos.seatarrangement.core.useradministration.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

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
    public ResponseEntity<AppUserData> fetchUser(Long appUserId) {
        return null;
    }

    @Override
    public ResponseEntity<Collection<AppUserData>> fetchAllUsers() {
        List<AppUser> users = userRepository.findAll();

//        List<AppUserData> userDatas = users.stream()
//                .map()
//                .collect(Collectors.toList());

//        return ResponseEntity.ok()
//                .body(userDatas);
        return null;
    }

    @Override
    public ResponseEntity getToken(TokenRequestData data) {

        validateLoginRequest(data);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));
        String token = tokenService.generateToken(authentication);

        return ResponseEntity.ok()
                .body(Map.of("status", "Success","token",token));
    }

    @Override
    public ResponseEntity<Boolean> confirmUsername(String username) {
        Optional<AppUser> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return ResponseEntity.ok()
                    .body(true);
        }
        return ResponseEntity.ok()
                .body(false);
    }

    private void validateLoginRequest(TokenRequestData data) {
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
}
