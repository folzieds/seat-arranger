package com.phos.seatarrangement.core.useradministration.service;

import com.phos.seatarrangement.core.useradministration.data.AppUserData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AppUserReadServiceImpl implements AppUserReadService{
    @Override
    public ResponseEntity<AppUserData> fetchUser(Long appUserId) {
        return null;
    }

    @Override
    public ResponseEntity<Collection<AppUserData>> fetchAllUsers() {
        return null;
    }
}
