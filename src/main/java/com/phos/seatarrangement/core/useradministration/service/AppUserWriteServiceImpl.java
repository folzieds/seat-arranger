package com.phos.seatarrangement.core.useradministration.service;

import com.phos.seatarrangement.core.useradministration.data.AppUserData;
import com.phos.seatarrangement.core.useradministration.data.AppUserResponseData;
import com.phos.seatarrangement.core.useradministration.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppUserWriteServiceImpl implements AppUserWriteService{

    private final UserRepository userRepository;

    public AppUserWriteServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<AppUserResponseData> create(AppUserData data) {
        validateForCreate(data);

        return null;
    }

    private void validateForCreate(AppUserData data) {

    }

    @Override
    public ResponseEntity<AppUserResponseData> update(AppUserData data) {
        return null;
    }

    @Override
    public ResponseEntity<AppUserResponseData> delete(Long appUserId) {
        return null;
    }
}
