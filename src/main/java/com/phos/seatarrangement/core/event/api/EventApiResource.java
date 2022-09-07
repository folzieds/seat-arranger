package com.phos.seatarrangement.core.event.api;

import com.phos.seatarrangement.core.event.data.EventDTO;
import com.phos.seatarrangement.core.event.service.EventReadService;
import com.phos.seatarrangement.core.event.service.EventWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public ResponseEntity create(@RequestBody EventDTO eventData){

        return ResponseEntity.ok()
                .body(Map.of("status", "success"));
    }

    @GetMapping("")
    public ResponseEntity retrieveAll(){

        return ResponseEntity.ok()
                .body(Map.of("status", "success"));
    }

    @GetMapping("{eventId}")
    public ResponseEntity retrieve(@PathVariable("eventId") Long eventId){

        return ResponseEntity.ok()
                .body(Map.of("status", "success"));
    }

    @PutMapping("{eventId}")
    public ResponseEntity update(@PathVariable("eventId") Long eventId){

        return ResponseEntity.ok()
                .body(Map.of("status", "success"));
    }

    @DeleteMapping("{eventId}")
    public ResponseEntity delete(@PathVariable("eventId") Long eventId){

        return ResponseEntity.ok()
                .body(Map.of("status", "success"));
    }
}
