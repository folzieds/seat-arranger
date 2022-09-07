package com.phos.seatarrangement.core.event.domain;

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
    private String requestId;

    private Event(String name, String address, LocalDate date, String requestId) {
        this.name = name;
        this.address = address;
        this.date = date;
        this.requestId = requestId;
    }

    public static Event build(final String name, final String address, final LocalDate date, final String requestId){
        return new Event(name, address, date, requestId);
    }

}
