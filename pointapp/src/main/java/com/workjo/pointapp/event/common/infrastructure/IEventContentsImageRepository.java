package com.workjo.pointapp.event.common.infrastructure;

import com.workjo.pointapp.event.common.domain.EventContentsImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEventContentsImageRepository extends JpaRepository<EventContentsImage, Long> {
    List<EventContentsImage> findByEventId(Long eventId);
}
