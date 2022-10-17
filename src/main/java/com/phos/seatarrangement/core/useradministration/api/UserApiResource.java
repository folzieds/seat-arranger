package com.phos.seatarrangement.core.useradministration.api;

import com.phos.seatarrangement.core.useradministration.data.AppUserData;
import com.phos.seatarrangement.core.useradministration.data.AppUserResponseData;
import com.phos.seatarrangement.core.useradministration.data.TokenRequestData;
import com.phos.seatarrangement.core.useradministration.service.AppUserReadService;
import com.phos.seatarrangement.core.useradministration.service.AppUserWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("users")
public class UserApiResource {

    private final AppUserReadService appUserReadService;
    private final AppUserWriteService appUserWriteService;

    @Autowired
    public UserApiResource(AppUserReadService appUserReadService, AppUserWriteService appUserWriteService) {
        this.appUserReadService = appUserReadService;
        this.appUserWriteService = appUserWriteService;
    }

    @PostMapping("")
    public ResponseEntity<AppUserResponseData> create(@RequestBody AppUserData appUserData){
        return null;
    }

    @GetMapping("confirm-username")
    public ResponseEntity<Boolean> confirmUsername(@RequestParam String username){
        return appUserReadService.confirmUsername(username);
    }

    @GetMapping("{id}")
    public ResponseEntity<AppUserData> fetchUser(@PathVariable("id")Long appUserId){
        return appUserReadService.fetchUser(appUserId);
    }

    @PutMapping("id")
    public ResponseEntity<AppUserResponseData> update(@PathVariable("id") Long appUserId){
        return null;
    }

    @GetMapping ("all")
    public ResponseEntity<Collection<AppUserData>> fetchAllUsers(){
        return appUserReadService.fetchAllUsers();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AppUserResponseData> delete(@PathVariable("id")Long appUserId){
        return null;
    }

    @PostMapping("oauth/token")
    public ResponseEntity token(@RequestBody TokenRequestData data){

        return appUserReadService.getToken(data);
    }

}
