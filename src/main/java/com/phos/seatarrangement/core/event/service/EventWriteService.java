package com.phos.seatarrangement.core.event.service;

import com.phos.seatarrangement.core.event.data.EventDTO;
import com.phos.seatarrangement.core.event.data.EventResponseDTO;
import org.springframework.http.ResponseEntity;

public interface EventWriteService {

    ResponseEntity<EventResponseDTO> create(EventDTO data);
}
