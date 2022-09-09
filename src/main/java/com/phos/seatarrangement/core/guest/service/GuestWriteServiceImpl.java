package com.phos.seatarrangement.core.guest.service;

import com.phos.seatarrangement.core.event.domain.Event;
import com.phos.seatarrangement.core.event.exception.EventNotFoundException;
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

import java.util.List;
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

    @Override
    public ResponseEntity<Map<String, Object>> deleteOne(String eventCode, Long guestId) {
        delete(eventCode, guestId);
        return ResponseEntity.ok()
                .body(Map.of("status", "Success", "resourceId", guestId));
    }

    @Override
    public ResponseEntity<Map<String, Object>> update(String eventCode, Long guestId) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteSelected(String eventCode, List<Long> guestIds) {
        for (Long guestId :
                guestIds) {
            delete(eventCode, guestId);
        }
        return ResponseEntity.ok()
                .body(Map.of("status", "Success"));
    }

    private void delete(String eventCode, Long guestId){
        try{
            logger.info("Fetching event with code {}", eventCode);
            Event event = eventRepository.findByEventCode(eventCode);

            if(event == null){
                throw new EventNotFoundException("error.msg.event.not.found",
                        String.format("The event with code %s was not found", eventCode));
            }

            Long eventId = event.getId();
            Guest guest = guestRepository.findByIdAndEventId(guestId, eventId);
            guestRepository.delete(guest);

            logger.info("Guest with id {} has been successfully deleted", guestId);
        }catch(Exception ex){
            logger.error("An error occurred while deleting guest {} from event with code {}", guestId, eventCode);
            throw new PlatformDataIntegrityException("error.msg.delete.one",
                    String.format("An error occurred while deleting guest %d from event with code %s", guestId, eventCode));
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteAll(String eventCode) {
        try{
            Event event = eventRepository.findByEventCode(eventCode);
            if(event != null){
                guestRepository.deleteAllByEventId(event.getId());
            }
            return ResponseEntity.ok()
                    .body(Map.of("status", "Success"));
        }catch (Exception ex){
            throw new PlatformDataIntegrityException("error.msg.delete.all",
                    String.format("An error occurred while trying to remove guests for event with code %s", eventCode));
        }
    }

    private ResponseEntity<Map<String, Object>> getGuestResponse(Long guestId, String eventCode){
        GuestResponseData response = new GuestResponseData(eventCode,guestId);
        return ResponseEntity.ok()
                .body(Map.of("status","Success", "data",response));
    }
}
