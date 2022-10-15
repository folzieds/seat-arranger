package com.phos.seatarrangement.event.repository;

import com.phos.seatarrangement.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

    Optional<Event> findByEventCode(String eventCode);

    @Query(value = "select * from m_event where name like :q", nativeQuery = true)
    List<Event> findAllByName(@Param("q") String q);

    @Query(value = "select event_code from m_event", nativeQuery = true)
    List<String> findAllCodes();
}
