package com.phos.seatarrangement.core.event.service;

import com.phos.seatarrangement.core.event.data.EventData;
import com.phos.seatarrangement.core.event.data.EventResponseData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventWriteService {

    ResponseEntity<EventResponseData> create(EventData data);

    ResponseEntity<EventResponseData> update(EventData data, String requestId);

    ResponseEntity<EventResponseData> deleteEvent(String requestId);

    ResponseEntity deleteAll(List<String> requestIds);
}
