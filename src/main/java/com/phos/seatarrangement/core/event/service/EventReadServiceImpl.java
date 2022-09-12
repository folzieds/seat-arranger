package com.phos.seatarrangement.core.event.service;

import com.phos.seatarrangement.core.event.data.EventData;
import com.phos.seatarrangement.core.event.domain.Event;
import com.phos.seatarrangement.core.event.exception.EventNotFoundException;
import com.phos.seatarrangement.core.event.repository.EventRepository;
import com.phos.seatarrangement.core.exception.PlatformDataIntegrityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventReadServiceImpl implements EventReadService{

    private final EventRepository eventRepository;

    private final Logger logger = LoggerFactory.getLogger(EventReadServiceImpl.class);

    @Autowired
    public EventReadServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getEvent(Long eventId) {
        try{
            logger.info("Fetching event with request id {}", eventId);
            Optional<Event> event = eventRepository.findById(eventId);

            return ResponseEntity.ok()
                    .body(Map.of("status", "success", "data", mapObjectToData(event.get())));
        }catch (Exception ex){
            logger.error("Event with event id {} was not found...", eventId);
            throw new EventNotFoundException("error.msg.event.not.found",
                    "The event was not found");
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAllEvents() {
        try{
            logger.info("Fetching all events...");
            List<Event> events = eventRepository.findAll();
            List<EventData> dataList = events.stream()
                    .map(this::mapObjectToData)
                    .collect(Collectors.toList());

            logger.info("Events successfully fetched");
            return ResponseEntity.ok()
                    .body(Map.of("status", "Success", "data", dataList));
        }catch (Exception ex){
            logger.error("An error occurred while fetching all events...");
            throw new EventNotFoundException("","",ex);
        }
    }

    @Override
    public ResponseEntity search( String q, Boolean exactMatch) {
        try{
            if (!exactMatch) {
                q += "%";
            }
            List<Event> events = eventRepository.findAllByName(q);
            return ResponseEntity.ok()
                    .body(Map.of("status", "success", "data", events));
        }catch (Exception ex){
            throw new PlatformDataIntegrityException("error.msg.search",
                    String.format("An error occurred while searching for the string '%s'", q));
        }
    }

    private EventData mapObjectToData(Event event){
        EventData data = new EventData();

        if(event != null){
            data.setDate(event.getDate());
            data.setName(event.getName());
            data.setAddress(event.getAddress());
        }
        return data;
    }

}
