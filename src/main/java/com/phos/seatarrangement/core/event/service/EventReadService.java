package com.phos.seatarrangement.core.event.service;


import com.phos.seatarrangement.core.event.data.EventData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventReadService {

    ResponseEntity<EventData> getEvent(String requestId);

    ResponseEntity<List<EventData>> getAllEvents();
}
