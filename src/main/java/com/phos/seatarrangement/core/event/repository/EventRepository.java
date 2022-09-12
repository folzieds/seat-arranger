package com.phos.seatarrangement.core.event.repository;

import com.phos.seatarrangement.core.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

    Event findByEventCode(String eventCode);

    @Query(value = "select * from m_event where name like :q", nativeQuery = true)
    List<Event> findAllByName(@Param("q") String q);
}
