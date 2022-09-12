package com.phos.seatarrangement.core.event.service;


import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface EventReadService {

    ResponseEntity<Map<String, Object>> getEvent(Long eventId);

    ResponseEntity<Map<String,Object>> getAllEvents();

    ResponseEntity search(String q, Boolean exactMatch);
}
