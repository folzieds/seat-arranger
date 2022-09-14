package com.phos.seatarrangement.core.guest.service;

import com.phos.seatarrangement.core.event.domain.Event;
import com.phos.seatarrangement.core.event.exception.EventNotFoundException;
import com.phos.seatarrangement.core.event.repository.EventRepository;
import com.phos.seatarrangement.core.exception.PlatformDataIntegrityException;
import com.phos.seatarrangement.core.guest.data.GuestData;
import com.phos.seatarrangement.core.guest.domain.Guest;
import com.phos.seatarrangement.core.guest.exception.GuestNotFoundException;
import com.phos.seatarrangement.core.guest.repository.GuestRepository;
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
    public GuestData retrieveOne(Long guestId, String eventCode){
        try{
            logger.info("Fetching guest with event code {}", eventCode);
            Optional<Event> optionalEvent = eventRepository.findByEventCode(eventCode);

            if(optionalEvent.isEmpty()){
                throw new EventNotFoundException("error.msg.event.not.found",
                        String.format("The event with code %s was not found", eventCode));
            }

            Event event = optionalEvent.get();
            Long eventId = event.getId();
            Optional<Guest> optionalGuest = guestRepository.findByIdAndEventId(guestId, eventId);

            if(optionalGuest.isEmpty()){
                throw new GuestNotFoundException("error.msg.guest.not.found",
                        String.format("Guest with event code %s and id %d not found",eventCode, guestId));
            }

            Guest guest = optionalGuest.get();

            return mapObjectToData(guest);
        }catch(Exception ex){
            throw new PlatformDataIntegrityException("error.msg.guest.fetch.one",
                    "An error occurred while retrieving guest");
        }
    }

    @Override
    public ResponseEntity search(String eventCode, String q, Boolean exactMatch) {
        try{
            Optional<Event> optionalEvent = eventRepository.findByEventCode(eventCode);

            if(optionalEvent.isEmpty()){
                throw new EventNotFoundException("error.msg.event.not.found",
                        String.format("Event with code %s was not found", eventCode));
            }

            Event event = optionalEvent.get();
            if (!exactMatch) {
                q += "%";
            }
            List<Guest> guests = guestRepository.findAllByEventIdAndName(event.getId(), q);
            List<GuestData> dataList = guests.stream()
                                            .map(this::mapObjectToData)
                                            .collect(Collectors.toList());
            return ResponseEntity.ok()
                    .body(Map.of("status", "success", "data", dataList));
        }catch (Exception ex){
            throw new PlatformDataIntegrityException("error.msg.search",
                    String.format("An error occurred while searching for the string '%s'", q));
        }
    }

    @Override
    public ResponseEntity retrieveAll(String eventCode) {
        try{
            logger.info("Fetching all guests with event code {}", eventCode);
            Optional<Event> optionalEvent = eventRepository.findByEventCode(eventCode);

            if(optionalEvent.isEmpty()){
                throw new EventNotFoundException("error.msg.event.not.found",
                        String.format("The event with code %s was not found", eventCode));
            }

            Event event = optionalEvent.get();
            Long eventId = event.getId();
            List<Guest> guests = guestRepository.findAllByEventId(eventId);
            List<GuestData> dataList = guests.stream()
                    .map(this::mapObjectToData)
                    .collect(Collectors.toList());

            return ResponseEntity.ok()
                    .body(Map.of("status", "success", "data", dataList));
        }catch(Exception ex){
            throw new PlatformDataIntegrityException("error.msg.guest.fetch.all",
                    "An error occurred while retrieving guests");
        }
    }

    private GuestData mapObjectToData(Guest guest){
        GuestData data = new GuestData();

        if(guest != null){
            data.setId(guest.getId());
            data.setFirstName(guest.getFirstName());
            data.setLastName(guest.getLastName());
            data.setTableNumber(guest.getTableNumber());
        }

        return data;
    }
}
