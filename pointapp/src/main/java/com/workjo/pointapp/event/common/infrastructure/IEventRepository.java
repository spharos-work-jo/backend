package com.workjo.pointapp.event.common.infrastructure;

import com.workjo.pointapp.event.common.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IEventRepository extends JpaRepository<Event, Long> {

    List<Event> findByEndDateBetween(LocalDateTime minEndDate, LocalDateTime maxEndDate);

    List<Event> findByDrawDateAfter(LocalDateTime minDrawDate);
}

