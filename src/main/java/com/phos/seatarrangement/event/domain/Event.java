package com.phos.seatarrangement.event.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "m_event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private LocalDate date;

    @Column(unique = true)
    private String eventCode;

    private Event(String name, String address, LocalDate date, String eventCode) {
        this.name = name;
        this.address = address;
        this.date = date;
        this.eventCode = eventCode;
    }

    public static Event build(final String name, final String address, final LocalDate date, final String eventCode){
        return new Event(name, address, date, eventCode);
    }

}
