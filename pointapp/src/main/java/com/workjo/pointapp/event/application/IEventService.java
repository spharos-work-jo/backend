package com.workjo.pointapp.event.application;

import com.workjo.pointapp.event.dto.EventEntityDto;
import com.workjo.pointapp.event.dto.GetEventsDto;

import java.util.List;

public interface IEventService {

    List<EventEntityDto> getEvents(GetEventsDto getDto);
}
