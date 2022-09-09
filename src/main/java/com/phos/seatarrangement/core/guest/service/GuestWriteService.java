package com.phos.seatarrangement.core.guest.service;

import com.phos.seatarrangement.core.guest.data.GuestData;
import com.phos.seatarrangement.core.guest.data.GuestResponseData;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface GuestWriteService {

    ResponseEntity<Map<String, Object>> create(String eventId, GuestData guestData);
}
