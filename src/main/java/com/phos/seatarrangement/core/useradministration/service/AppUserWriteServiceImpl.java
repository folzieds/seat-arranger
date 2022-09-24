package com.phos.seatarrangement.core.useradministration.service;

import com.phos.seatarrangement.core.useradministration.data.AppUserData;
import com.phos.seatarrangement.core.useradministration.data.AppUserResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppUserWriteServiceImpl implements AppUserWriteService{
    @Override
    public ResponseEntity<AppUserResponseData> create(AppUserData data) {
        return null;
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
