package com.phos.seatarrangement.core.guest.service;

import com.phos.seatarrangement.core.event.domain.Event;
import com.phos.seatarrangement.core.event.repository.EventRepository;
import com.phos.seatarrangement.core.exception.PlatformDataIntegrityException;
import com.phos.seatarrangement.core.guest.data.GuestData;
import com.phos.seatarrangement.core.guest.data.GuestResponseData;
import com.phos.seatarrangement.core.guest.domain.Guest;
import com.phos.seatarrangement.core.guest.repository.GuestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GuestWriteServiceImpl implements GuestWriteService{

    Logger logger = LoggerFactory.getLogger(GuestWriteServiceImpl.class);

    private final GuestRepository guestRepository;

    private final EventRepository eventRepository;

    @Autowired
    public GuestWriteServiceImpl(GuestRepository guestRepository, EventRepository eventRepository) {
        this.guestRepository = guestRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> create(String eventCode, GuestData guestData) {
        try{
            logger.info("Fetching event with code {}", eventCode);
            Event event = eventRepository.findByEventCode(eventCode);

            String firstName = guestData.getFirstName();
            String lastName = guestData.getLastName();
            String tableNumber = guestData.getTableNumber();

            Guest guest = guestRepository.save(Guest.build(firstName,lastName,tableNumber,event));
            logger.info("Guest saved under event with code {}", eventCode);

            return getGuestResponse(guest.getId(), eventCode);
        }catch(Exception ex){
            logger.error("An error occurred while creating a new guest for the event with id {}", eventCode);
            throw new PlatformDataIntegrityException("error.msg.guest.creation",
                    String.format("The Guest %s could not be created and added to the event", guestData.getFirstName()));
        }
    }

    private ResponseEntity<Map<String, Object>> getGuestResponse(Long guestId, String eventCode){
        GuestResponseData response = new GuestResponseData(eventCode,guestId);
        return ResponseEntity.ok()
                .body(Map.of("status","Success", "data",response));
    }
}
