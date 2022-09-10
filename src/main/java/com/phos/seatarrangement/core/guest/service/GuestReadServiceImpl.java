package com.phos.seatarrangement.core.guest.service;

import com.phos.seatarrangement.core.event.domain.Event;
import com.phos.seatarrangement.core.event.exception.EventNotFoundException;
import com.phos.seatarrangement.core.event.repository.EventRepository;
import com.phos.seatarrangement.core.exception.PlatformDataIntegrityException;
import com.phos.seatarrangement.core.guest.domain.Guest;
import com.phos.seatarrangement.core.guest.repository.GuestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GuestReadServiceImpl implements GuestReadService{

    Logger logger = LoggerFactory.getLogger(GuestReadServiceImpl.class);

    private final EventRepository eventRepository;
    private final GuestRepository guestRepository;

    @Autowired
    public GuestReadServiceImpl(EventRepository eventRepository, GuestRepository guestRepository) {
        this.eventRepository = eventRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public Guest retrieveOne(Long guestId, String eventCode){
        try{
            logger.info("Fetching event with code {}", eventCode);
            Event event = eventRepository.findByEventCode(eventCode);

            if(event == null){
                throw new EventNotFoundException("error.msg.event.not.found",
                        String.format("The event with code %s was not found", eventCode));
            }

            Long eventId = event.getId();
            Guest guest = guestRepository.findByIdAndEventId(guestId, eventId);

            return guest;
        }catch(Exception ex){
            throw new PlatformDataIntegrityException("error.msg.guest.fetch.one",
                    "An error occurred while retrieving guest");
        }
    }

    @Override
    public ResponseEntity search(String eventCode, String q, Boolean exactMatch) {
        return null;
    }

    @Override
    public ResponseEntity retrieveAll(String eventCode) {
        try{
            logger.info("Fetching event with code {}", eventCode);
            Event event = eventRepository.findByEventCode(eventCode);

            if(event == null){
                throw new EventNotFoundException("error.msg.event.not.found",
                        String.format("The event with code %s was not found", eventCode));
            }

            Long eventId = event.getId();
            List<Guest> guests = guestRepository.findAllByEventId(eventId);

            return ResponseEntity.ok()
                    .body(Map.of("status", "success", "data", guests));
        }catch(Exception ex){
            throw new PlatformDataIntegrityException("error.msg.guest.fetch.all",
                    "An error occurred while retrieving guests");
        }
    }
}
