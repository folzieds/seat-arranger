package com.phos.seatarrangement.core.guest.service;

import com.phos.seatarrangement.core.guest.data.GuestData;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface GuestWriteService {

    ResponseEntity<Map<String, Object>> create(String eventId, GuestData guestData);

    ResponseEntity<Map<String, Object>> deleteOne(String eventCode, Long guestId);

    ResponseEntity<Map<String, Object>> update(String eventCode, Long guestId);

    ResponseEntity<Map<String, Object>> deleteSelected(String eventCode, List<Long> guestIds);

    ResponseEntity<Map<String, Object>> deleteAll(String eventCode);
}
