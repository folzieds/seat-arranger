package com.phos.seatarrangement.core.guest.service;

import com.phos.seatarrangement.core.guest.domain.Guest;

public interface GuestReadService {

    public Guest retrieveOne(Long guestId, String eventCode);
}
