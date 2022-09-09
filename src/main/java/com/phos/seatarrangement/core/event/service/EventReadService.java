package com.phos.seatarrangement.core.event.service;


import com.phos.seatarrangement.core.event.data.EventData;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface EventReadService {

    ResponseEntity<Map<String, Object>> getEvent(String requestId);

    ResponseEntity<Map<String,Object>> getAllEvents();
}
