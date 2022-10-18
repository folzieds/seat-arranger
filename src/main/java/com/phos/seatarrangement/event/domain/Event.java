package com.phos.seatarrangement.event.domain;

import com.phos.seatarrangement.core.useradministration.domain.AppUser;
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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @Column(unique = true)
    private String eventCode;

    private Event(String name, String address, LocalDate date, String eventCode, AppUser user) {
        this.name = name;
        this.address = address;
        this.date = date;
        this.eventCode = eventCode;
        this.user = user;
    }

    public static Event build(final String name, final String address, final LocalDate date,
                              final String eventCode, final AppUser user){
        return new Event(name, address, date, eventCode, user);
    }

}
