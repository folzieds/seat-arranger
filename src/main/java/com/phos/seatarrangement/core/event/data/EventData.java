package com.phos.seatarrangement.core.event.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventData {

    private String name;
    private LocalDate date;
    private String address;


}
