package com.phos.seatarrangement.core.useradministration.api;

import com.phos.seatarrangement.core.useradministration.data.AppUserData;
import com.phos.seatarrangement.core.useradministration.data.AppUserResponseData;
import com.phos.seatarrangement.core.useradministration.service.AppUserReadService;
import com.phos.seatarrangement.core.useradministration.service.AppUserWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<AppUserResponseData> create(){
        return null;
    }

    @GetMapping("{id}")
    public ResponseEntity<AppUserData> fetchUser(@PathVariable("id")Long appUserId){
        return null;
    }
}
