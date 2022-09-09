package com.phos.seatarrangement.core.guest.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestResponseData {
    private String eventCode;
    private Long guestId;
}
