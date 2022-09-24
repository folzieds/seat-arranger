package com.phos.seatarrangement.core.useradministration.service;

import com.phos.seatarrangement.core.useradministration.data.AppUserData;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface AppUserReadService {

    ResponseEntity<AppUserData> fetchUser(Long appUserId);

    ResponseEntity<Collection<AppUserData>> fetchAllUsers();
}