package com.phos.seatarrangement.core.useradministration.service;

import com.phos.seatarrangement.core.useradministration.data.AppUserData;
import com.phos.seatarrangement.core.useradministration.data.AppUserResponseData;
import org.springframework.http.ResponseEntity;

public interface AppUserWriteService {

    ResponseEntity<AppUserResponseData> create(AppUserData data);

    ResponseEntity<AppUserResponseData> activate(Long userId);

    ResponseEntity<AppUserResponseData> update(AppUserData data);

    ResponseEntity<AppUserResponseData> delete(Long appUserId);
}
