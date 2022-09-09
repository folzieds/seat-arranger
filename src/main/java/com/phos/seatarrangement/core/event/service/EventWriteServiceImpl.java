package com.phos.seatarrangement.core.event.service;

import com.phos.seatarrangement.core.event.data.EventData;
import com.phos.seatarrangement.core.event.data.EventResponseData;
import com.phos.seatarrangement.core.event.domain.Event;
import com.phos.seatarrangement.core.event.exception.EventNotFoundException;
import com.phos.seatarrangement.core.event.repository.EventRepository;
import com.phos.seatarrangement.core.exception.PlatformDataIntegrityException;
import com.phos.seatarrangement.core.guest.repository.GuestRepository;
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

    @Autowired
    public EventWriteServiceImpl(EventRepository eventRepository, GuestRepository guestRepository) {
        this.eventRepository = eventRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public ResponseEntity<EventResponseData> create(EventData data) {
        try{
            validateData(data);
            String eventCode = getRandomString();
            String name = data.getName();
            String address = data.getAddress();
            LocalDate date = data.getDate();

            Event event =  eventRepository.save(Event.build(name, address, date, eventCode));

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

    private String getRandomString() {
        return RandomStringUtils.random(15, true, true);
    }

    @Override
    public ResponseEntity<EventResponseData> update(EventData data, Long eventId) {
        try{
            validateData(data);
            Optional<Event> optionalEvent = eventRepository.findById(eventId);


            if(!optionalEvent.isEmpty()){
                throw new EventNotFoundException("error.msg.event.not.found",
                        "The event was not found");
            }
            Event event = optionalEvent.get();

            if( data != null  && !data.getName().equals(event.getName())){
                event.setName(data.getName());
            }
            if( data != null  && !data.getAddress().equals(event.getAddress())){
                event.setAddress(data.getAddress());
            }
            if( data != null  && data.getDate() != event.getDate()){
                event.setDate(data.getDate());
            }

            eventRepository.save(event);
            return getEventResponse(event);
        }catch (Exception ex){
            logger.error("An error occurred while updating event...");
            throw new EventNotFoundException("error.msg.event.not.updated"
                    ,String.format("The event with event id %d could not be updated...", eventId));
        }
    }

    private void validateData(EventData data) {

    }

    @Override
    public ResponseEntity<EventResponseData> deleteEvent(Long eventId) {
        Event event = delete(eventId);
        return getEventResponse(event);
    }

    @Override
    public ResponseEntity deleteAll() {
        // refactor when you add users
        eventRepository.deleteAll();
        return ResponseEntity.ok()
                .body(Map.of("status", "Success"));
    }

    private Event delete(Long eventId){
        try{

            Optional<Event> optionalEvent = eventRepository.findById(eventId);

            if(!optionalEvent.isEmpty()){
                throw new EventNotFoundException("error.msg.event.not.deleted"
                        ,String.format("The event with id %d could not be deleted...", eventId));
            }
            Event event = optionalEvent.get();

            logger.info("Deleting all guests associated with event -> {}", eventId);
            guestRepository.deleteAllByEventId(event.getId());

            eventRepository.delete(event);
            logger.info("Event with id {} has been deleted", eventId);

            return event;
        }catch (Exception ex){
            logger.error("An error occurred while trying to delete event...");
            throw new EventNotFoundException("error.msg.event.not.deleted"
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
