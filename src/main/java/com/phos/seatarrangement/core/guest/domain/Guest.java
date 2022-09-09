package com.phos.seatarrangement.core.guest.domain;

import com.phos.seatarrangement.core.event.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "m_guest")
@NoArgsConstructor
@AllArgsConstructor
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String tableNumber;

    @OneToOne
    private Event event;

    private Guest(String firstName, String lastName, String tableNumber, Event event){
        this.firstName = firstName;
        this.lastName = lastName;
        this.tableNumber = tableNumber;
        this.event = event;
    }

    public static Guest build(String firstName, String lastName, String tableNumber, Event event){
        return new Guest(firstName,lastName,tableNumber, event);
    }


}
