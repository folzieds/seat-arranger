package com.phos.seatarrangement.core.useradministration.service;

import com.phos.seatarrangement.core.useradministration.data.AppUserData;
import com.phos.seatarrangement.core.useradministration.data.TokenRequestData;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Collection;

public interface AppUserReadService {

    ResponseEntity<AppUserData> fetchUser(Long appUserId);

    ResponseEntity<Collection<AppUserData>> fetchAllUsers();

    ResponseEntity getToken(TokenRequestData data);
}
