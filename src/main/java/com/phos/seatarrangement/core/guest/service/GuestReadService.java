package com.phos.seatarrangement.core.guest.service;

import com.phos.seatarrangement.core.guest.domain.Guest;
import org.springframework.http.ResponseEntity;

public interface GuestReadService {

    Guest retrieveOne(Long guestId, String eventCode);

    ResponseEntity search(String eventCode, String q, Boolean exactMatch);

    ResponseEntity retrieveAll(String eventCode);
}
