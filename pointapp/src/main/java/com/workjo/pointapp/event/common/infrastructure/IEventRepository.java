package com.workjo.pointapp.event.common.infrastructure;


import com.workjo.pointapp.event.common.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface IEventRepository extends JpaRepository<Event, Long> {

	List<Event> findByEndDateBefore(LocalDateTime endDate);

	List<Event> findByEndDateAfter(LocalDateTime endDate);

	List<Event> findByDrawDateBefore(LocalDateTime drawDate);
	List<Event> findByDrawDateBetween(LocalDateTime drawDate1, LocalDateTime drawDate2);

}

