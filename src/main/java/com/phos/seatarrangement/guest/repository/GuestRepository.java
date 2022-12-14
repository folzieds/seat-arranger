package com.phos.seatarrangement.guest.repository;

import com.phos.seatarrangement.guest.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Long>, JpaSpecificationExecutor<Guest> {

    Optional<Guest> findByIdAndEventId(Long guestId, Long eventId);

    List<Guest> findAllByEventId(Long eventId);

    @Query(value = "select * from m_guest where event_id = :event_id and first_name like :q or last_name like :q", nativeQuery = true)
    List<Guest> findAllByEventIdAndName(@Param("event_id") Long id, @Param("q") String q);
}
