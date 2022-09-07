package com.phos.seatarrangement.core.guest.api;

import com.phos.seatarrangement.core.event.data.EventDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("guest")
public class GuestApiResource {

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

    @GetMapping("{guestId}")
    public ResponseEntity retrieve(@PathVariable("guestId") Long eventId){

        return ResponseEntity.ok()
                .body(Map.of("status", "success"));
    }

    @PutMapping("{guestId}")
    public ResponseEntity update(@PathVariable("guestId") Long eventId){

        return ResponseEntity.ok()
                .body(Map.of("status", "success"));
    }

    @DeleteMapping("{guestId}")
    public ResponseEntity delete(@PathVariable("guestId") Long eventId){

        return ResponseEntity.ok()
                .body(Map.of("status", "success"));
    }
}
