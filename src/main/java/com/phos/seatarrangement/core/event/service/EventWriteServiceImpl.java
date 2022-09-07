package com.phos.seatarrangement.core.event.service;

import com.phos.seatarrangement.core.event.data.EventDTO;
import com.phos.seatarrangement.core.event.data.EventResponseDTO;
import com.phos.seatarrangement.core.event.domain.Event;
import com.phos.seatarrangement.core.event.repository.EventRepository;
import com.phos.seatarrangement.core.exception.PlatformDataIntegrityException;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EventWriteServiceImpl implements EventWriteService{

    private final Logger logger = LoggerFactory.getLogger(EventWriteServiceImpl.class);

    private final EventRepository eventRepository;

    @Autowired
    public EventWriteServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public ResponseEntity<EventResponseDTO> create(EventDTO data) {
        try{
            String requestId = getRandomString();
            String name = data.getName();
            String address = data.getAddress();
            LocalDate date = data.getDate();

            Event event =  eventRepository.save(Event.build(name, address, date, requestId));

            logger.info("{} event has been created", name);
            return getEventResponse(event);
        }catch (Exception ex){
            logger.error("The {} event could not be created...", data.getName());
            throw new PlatformDataIntegrityException("error.msg.event.creation",
                    String.format("The %s event could not be created...", data.getName()));
        }

        // we would also need the url, but I don't need to save it as it follows a similar pattern
        // we would add guests after the event has been created
        // either one by one or as a bulk
        // Also there would be the option of uploading a file


    }

    private ResponseEntity<EventResponseDTO> getEventResponse(Event event) {
        Long eventId = event.getId();
        String requestId = event.getRequestId();

        return ResponseEntity.ok()
                .body(new EventResponseDTO(requestId,eventId));
    }

    private String getRandomString() {
        return RandomStringUtils.random(15, true, true);
    }
}
