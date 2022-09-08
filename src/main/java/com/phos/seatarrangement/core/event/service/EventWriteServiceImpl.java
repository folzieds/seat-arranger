package com.phos.seatarrangement.core.event.service;

import com.phos.seatarrangement.core.event.data.EventDTO;
import com.phos.seatarrangement.core.event.data.EventResponseDTO;
import com.phos.seatarrangement.core.event.domain.Event;
import com.phos.seatarrangement.core.event.exception.EventNotFoundException;
import com.phos.seatarrangement.core.event.repository.EventRepository;
import com.phos.seatarrangement.core.exception.PlatformDataIntegrityException;
import com.phos.seatarrangement.core.guest.GuestRepository;
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

    private final GuestRepository guestRepository;

    @Autowired
    public EventWriteServiceImpl(EventRepository eventRepository, GuestRepository guestRepository) {
        this.eventRepository = eventRepository;
        this.guestRepository = guestRepository;
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

    @Override
    public ResponseEntity<EventResponseDTO> update(EventDTO data, String requestId) {
        try{

            Event event = eventRepository.findByRequestId(requestId);
            return getEventResponse(null);
        }catch (Exception ex){
            throw new EventNotFoundException("error.msg.event.not.updated"
                    ,String.format("The %s event could not be updated...", data.getName()));
        }
    }

    @Override
    public ResponseEntity<EventResponseDTO> delete(String requestId) {
        try{

            Event event = eventRepository.findByRequestId(requestId);
            if(event == null){
                throw new EventNotFoundException("error.msg.event.not.deleted"
                        ,String.format("The event with id %s could not be deleted...", requestId));
            }

            logger.info("Deleting all guests associated with event -> {}", requestId);
            guestRepository.deleteAllByEventId(event.getId());

            eventRepository.delete(event);
            logger.info("Event with request id {} has been deleted", requestId);

            return getEventResponse(event);
        }catch (Exception ex){
            throw new EventNotFoundException("error.msg.event.not.deleted"
                    ,String.format("The event with id %s could not be deleted...", requestId));
        }
    }
}
