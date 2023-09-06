package com.workjo.pointapp.event.infrastructure;

import com.workjo.pointapp.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IEventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findById(Long id);

    List<Event> findByEndDateBetween(LocalDateTime findStartDate, LocalDateTime findEndDate);
}

