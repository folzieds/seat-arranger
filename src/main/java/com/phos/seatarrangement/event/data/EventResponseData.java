package com.phos.seatarrangement.event.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseData {

    private String requestId;
    private Long eventId;
}
