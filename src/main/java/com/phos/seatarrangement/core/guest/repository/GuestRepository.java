package com.phos.seatarrangement.core.guest;

import com.phos.seatarrangement.core.guest.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GuestRepository extends JpaRepository<Guest, Long>, JpaSpecificationExecutor<Guest> {

    @Query(value = "delete from m_guest where event_id = :id", nativeQuery = true)
    void deleteAllByEventId(@Param("id") Long id);
}
