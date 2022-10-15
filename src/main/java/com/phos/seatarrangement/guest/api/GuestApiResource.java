package com.phos.seatarrangement.guest.api;

import com.phos.seatarrangement.guest.data.GuestData;
import com.phos.seatarrangement.guest.service.GuestReadService;
import com.phos.seatarrangement.guest.service.GuestWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("event/{eventCode}/guest")
public class GuestApiResource {

    private final GuestReadService guestReadService;
    private final GuestWriteService guestWriteService;

    @Autowired
    public GuestApiResource(GuestReadService guestReadService, GuestWriteService guestWriteService) {
        this.guestReadService = guestReadService;
        this.guestWriteService = guestWriteService;
    }

    @PostMapping("")
    public ResponseEntity create(@PathVariable("eventCode") String eventCode,@RequestBody GuestData guestData){
        return guestWriteService.create(eventCode, guestData);
    }

    @GetMapping("")
    public ResponseEntity retrieveAll(@PathVariable("eventCode") String eventCode){
        return guestReadService.retrieveAll(eventCode);
    }

    @GetMapping("{guestId}")
    public ResponseEntity retrieve(@PathVariable("eventCode") String eventCode,
                                   @PathVariable("guestId") Long guestId){
        GuestData guest = guestReadService.retrieveOne(guestId, eventCode);
        return ResponseEntity.ok()
                .body(Map.of("status", "success", "data", guest));
    }

    @GetMapping("search")
    public ResponseEntity search(@PathVariable("eventCode") String eventCode,
                                 @RequestParam("q") String q,
                                 @RequestParam(value = "exactMatch", defaultValue = "false") Boolean exactMatch){
        return guestReadService.search(eventCode, q, exactMatch);
    }

    @PutMapping("{guestId}")
    public ResponseEntity update(@PathVariable("eventCode") String eventCode,
                                 @PathVariable("guestId") Long guestId,
                                 @RequestBody GuestData data){

        return guestWriteService.update(eventCode, guestId, data);
    }

    @DeleteMapping("{guestId}")
    public ResponseEntity delete(@PathVariable("eventCode") String eventCode,
                                 @PathVariable("guestId") Long guestId){

        return guestWriteService.deleteOne(eventCode, guestId);
    }

    @DeleteMapping("")
    public ResponseEntity deleteSelected(@PathVariable("eventCode") String eventCode,
                                 @RequestParam("guestIds") List<Long> guestIds){

        return guestWriteService.deleteSelected(eventCode, guestIds);
    }

    @DeleteMapping("all")
    public ResponseEntity deleteSelected(@PathVariable("eventCode") String eventCode){

        return guestWriteService.deleteAll(eventCode);
    }
}
