package com.workjo.pointapp.event.application;

import com.workjo.pointapp.event.domain.Event;
import com.workjo.pointapp.event.dto.EventEntityDto;
import com.workjo.pointapp.event.dto.GetEventsDto;
import com.workjo.pointapp.event.infrastructure.IEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImple implements IEventService{
    private final IEventRepository eventRepository;
    private final ModelMapper modelMapper;

    public List<EventEntityDto> getEvents(GetEventsDto getDto) {

        List<Event> eventEntities
                = eventRepository.findByEndDateBetween(getDto.getMinEndDate(), getDto.getMaxEndDate());

        List<EventEntityDto> eventsDto =
                eventEntities.stream()
                        .map(event -> modelMapper.map(event, EventEntityDto.class))
                        .collect(Collectors.toList());

        return eventsDto;
    }


}
