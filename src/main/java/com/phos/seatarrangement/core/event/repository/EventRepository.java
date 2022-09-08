package com.phos.seatarrangement.core.event.repository;

import com.phos.seatarrangement.core.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

    Event findByRequestId(String requestId);
}
