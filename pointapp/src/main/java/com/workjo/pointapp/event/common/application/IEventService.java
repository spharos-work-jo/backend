package com.workjo.pointapp.event.common.application;

import com.workjo.pointapp.event.common.dto.EventContentsImageDto;
import com.workjo.pointapp.event.common.dto.EventDto;
import com.workjo.pointapp.event.common.dto.FindEventByStatusDto;

import java.util.List;

public interface IEventService {

    EventDto findById(Long id);

    List<EventDto> findByStatus(FindEventByStatusDto findEventByStatusDto);

    List<EventContentsImageDto> findEventContentsImages(Long eventId);
}
