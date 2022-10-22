package com.phos.seatarrangement.event.service;

import com.phos.seatarrangement.core.useradministration.domain.AppUser;
import com.phos.seatarrangement.core.useradministration.service.AppUserReadService;
import com.phos.seatarrangement.event.data.EventData;
import com.phos.seatarrangement.event.data.EventResponseData;
import com.phos.seatarrangement.event.domain.Event;
import com.phos.seatarrangement.event.exception.EventNotFoundException;
import com.phos.seatarrangement.event.repository.EventRepository;
import com.phos.seatarrangement.core.exception.PlatformDataIntegrityException;
import com.phos.seatarrangement.guest.domain.Guest;
import com.phos.seatarrangement.guest.repository.GuestRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EventWriteServiceImpl implements EventWriteService{

    private final Logger logger = LoggerFactory.getLogger(EventWriteServiceImpl.class);

    private final EventRepository eventRepository;

    private final GuestRepository guestRepository;

    private final AppUserReadService appUserReadService;

    @Autowired
    public EventWriteServiceImpl(EventRepository eventRepository, GuestRepository guestRepository, AppUserReadService appUserReadService) {
        this.eventRepository = eventRepository;
        this.guestRepository = guestRepository;
        this.appUserReadService = appUserReadService;
    }

    @Override
    public ResponseEntity<EventResponseData> create(EventData data) {
        try{
            validateData(data);
            List<String> codes = eventRepository.findAllCodes();
            String eventCode = getRandomString();
            while(!isUnique(eventCode, codes)){
                eventCode = getRandomString();
            }
            String name = data.getName();
            String address = data.getAddress();
            LocalDate date = data.getDate();

            AppUser user = appUserReadService.getCurrentUser();

            Event event =  eventRepository.save(Event.build(name, address, date, eventCode, user));

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

    private ResponseEntity<EventResponseData> getEventResponse(Event event) {
        Long eventId = event.getId();
        String eventCode = event.getEventCode();

        return ResponseEntity.ok()
                .body(new EventResponseData(eventCode,eventId));
    }

    private Boolean isUnique(String code, List<String> codes){
        return !codes.contains(code);
    }

    private String getRandomString() {
        return RandomStringUtils.random(15, true, true);
    }

    @Override
    public ResponseEntity<EventResponseData> update(EventData data, Long eventId) {
        try{
            validateData(data);
            Optional<Event> optionalEvent = eventRepository.findById(eventId);


            if(optionalEvent.isEmpty()){
                throw new EventNotFoundException("error.msg.event.not.found",
                        "The event was not found");
            }
            Event event = optionalEvent.get();

            if( data != null  && data.getName() != null && !data.getName().equals(event.getName())){
                event.setName(data.getName());
            }
            if( data != null && data.getAddress() != null  && !data.getAddress().equals(event.getAddress())){
                event.setAddress(data.getAddress());
            }
            if( data != null && data.getDate() != null && data.getDate() != event.getDate()){
                event.setDate(data.getDate());
            }

            eventRepository.save(event);
            return getEventResponse(event);
        }catch (Exception ex){
            logger.error("An error occurred while updating event...");
            throw new PlatformDataIntegrityException("error.msg.event.not.updated"
                    ,String.format("The event with event id %d could not be updated...", eventId));
        }
    }

    private void validateData(EventData data) {
        //Todo: Validate event data
    }

    @Override
    public ResponseEntity<EventResponseData> deleteEvent(Long eventId) {
        Event event = delete(eventId);
        return getEventResponse(event);
    }

    @Override
    public ResponseEntity deleteAll() {

        logger.info("Fetching logged in user...");
        AppUser user = appUserReadService.getCurrentUser();

        logger.info("Deleting all events for user with id {}", user.getId());

        eventRepository.deleteAllByUser(user);
        return ResponseEntity.ok()
                .body(Map.of("status", "Success"));
    }

    private Event delete(Long eventId){
        try{

            Optional<Event> optionalEvent = eventRepository.findById(eventId);

            if(optionalEvent.isEmpty()){
                throw new EventNotFoundException("error.msg.event.not.found"
                        ,String.format("The event with id %d could not be found...", eventId));
            }
            Event event = optionalEvent.get();

            logger.info("Deleting all guests associated with event -> {}", eventId);
            List<Guest> guests = guestRepository.findAllByEventId(event.getId());
            guestRepository.deleteAll(guests);

            eventRepository.delete(event);
            logger.info("Event with id {} has been deleted", eventId);

            return event;
        }catch (Exception ex){
            logger.error("An error occurred while trying to delete event...");
            throw new PlatformDataIntegrityException("error.msg.event.not.deleted"
                    ,String.format("The event with id %d could not be deleted...", eventId));
        }
    }

    @Override
    public ResponseEntity deleteSelected(List<Long> eventIds) {
        for (Long eventId :
                eventIds) {
            delete(eventId);
        }
        return ResponseEntity.ok()
                .body(Map.of("status", "Success"));
    }
}
