package com.phos.seatarrangement.event.api;

import com.phos.seatarrangement.event.data.EventData;
import com.phos.seatarrangement.event.service.EventReadService;
import com.phos.seatarrangement.event.service.EventWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("event")
public class EventApiResource {

    private final EventReadService eventReadService;
    private final EventWriteService eventWriteService;

    @Autowired
    public EventApiResource(EventReadService eventReadService, EventWriteService eventWriteService) {
        this.eventReadService = eventReadService;
        this.eventWriteService = eventWriteService;
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody EventData eventData){

        return eventWriteService.create(eventData);
    }

    @GetMapping("")
    public ResponseEntity retrieveAll(){

        return eventReadService.getAllEvents();
    }

    @GetMapping("search")
    public ResponseEntity search(@RequestParam("q")String q, @RequestParam(value = "exactMatch", defaultValue = "false") Boolean exactMatch){

        return eventReadService.search(q, exactMatch);
    }

    @GetMapping("{eventId}")
    public ResponseEntity retrieve(@PathVariable("eventId") Long eventId){

        return eventReadService.getEvent(eventId);
    }

    @PutMapping("{eventId}")
    public ResponseEntity update(@PathVariable("eventId") Long eventId, @RequestBody EventData data){

        return eventWriteService.update(data,eventId);
    }

    @DeleteMapping("{eventId}")
    public ResponseEntity delete(@PathVariable("eventId") Long eventId){

        return eventWriteService.deleteEvent(eventId);
    }

    @DeleteMapping("")
    public ResponseEntity deleteAll(){

        return eventWriteService.deleteAll();
    }
}
