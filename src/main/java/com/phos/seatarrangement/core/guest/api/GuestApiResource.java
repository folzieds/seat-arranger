package com.phos.seatarrangement.core.guest.api;

import com.phos.seatarrangement.core.event.data.EventData;
import com.phos.seatarrangement.core.guest.service.GuestReadService;
import com.phos.seatarrangement.core.guest.service.GuestWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("guest")
public class GuestApiResource {

    private final GuestReadService guestReadService;
    private final GuestWriteService guestWriteService;

    @Autowired
    public GuestApiResource(GuestReadService guestReadService, GuestWriteService guestWriteService) {
        this.guestReadService = guestReadService;
        this.guestWriteService = guestWriteService;
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody EventData eventData){

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
