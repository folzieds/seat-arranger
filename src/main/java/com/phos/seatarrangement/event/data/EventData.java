package com.phos.seatarrangement.event.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventData {

    private Long id;
    private String name;
    private LocalDate date;
    private String address;
    private String eventCode;


}
