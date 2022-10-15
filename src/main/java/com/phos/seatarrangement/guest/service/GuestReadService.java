package com.phos.seatarrangement.guest.service;

import com.phos.seatarrangement.guest.data.GuestData;
import org.springframework.http.ResponseEntity;

public interface GuestReadService {

    GuestData retrieveOne(Long guestId, String eventCode);

    ResponseEntity search(String eventCode, String q, Boolean exactMatch);

    ResponseEntity retrieveAll(String eventCode);
}
