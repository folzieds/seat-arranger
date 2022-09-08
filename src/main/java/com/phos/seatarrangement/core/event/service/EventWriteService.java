package com.phos.seatarrangement.core.event.service;

import com.phos.seatarrangement.core.event.data.EventDTO;
import com.phos.seatarrangement.core.event.data.EventResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventWriteService {

    ResponseEntity<EventResponseDTO> create(EventDTO data);

    ResponseEntity<EventResponseDTO> update(EventDTO data, String requestId);

    ResponseEntity<EventResponseDTO> deleteEvent(String requestId);

    ResponseEntity deleteAll(List<String> requestIds);
}
