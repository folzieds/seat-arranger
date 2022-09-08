package com.phos.seatarrangement.core.event.service;

import com.phos.seatarrangement.core.event.data.EventData;
import com.phos.seatarrangement.core.event.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventReadServiceImpl implements EventReadService{

    private final EventRepository eventRepository;

    private final Logger logger = LoggerFactory.getLogger(EventReadServiceImpl.class);

    public EventReadServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public ResponseEntity<EventData> getEvent(String requestId) {
        logger.info("Fetching event with request id {}", requestId);
        return null;
    }

    @Override
    public ResponseEntity<List<EventData>> getAllEvents() {
        return null;
    }
}
