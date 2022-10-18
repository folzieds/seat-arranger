package com.phos.seatarrangement.core.useradministration.service;

import com.phos.seatarrangement.core.useradministration.data.AppUserData;
import com.phos.seatarrangement.core.useradministration.data.TokenRequestData;
import com.phos.seatarrangement.core.useradministration.domain.AppUser;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface AppUserReadService {

    ResponseEntity fetchUser(Long appUserId);

    ResponseEntity<Collection<AppUserData>> fetchAllUsers();

    ResponseEntity getToken(TokenRequestData data);

    ResponseEntity<Boolean> confirmUsername(String username);

    AppUser getCurrentUser();
}
