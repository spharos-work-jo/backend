package com.workjo.pointapp.event.common.application;


import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.event.common.domain.Event;
import com.workjo.pointapp.event.common.domain.EventContentsImage;
import com.workjo.pointapp.event.common.dto.EventContentsImageDto;
import com.workjo.pointapp.event.common.dto.EventDto;
import com.workjo.pointapp.event.common.dto.FindEventByStatusDto;
import com.workjo.pointapp.event.common.infrastructure.IEventContentsImageRepository;
import com.workjo.pointapp.event.common.infrastructure.IEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@SuppressWarnings("RedundantLabeledSwitchRuleCodeBlock")
@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImple implements IEventService {

	private final IEventRepository eventRepository;
	private final IEventContentsImageRepository eventContentsImageRepository;
	private final ModelMapper modelMapper;


	@Override
	public EventDto findById(Long id) {
		Event event =
			eventRepository.findById(id)
				.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));

		return modelMapper.map(event, EventDto.class);
	}


	@Override
	public List<EventDto> findByStatus(FindEventByStatusDto findDto) {
		List<Event> events;
		switch (findDto.getEventStatus()) {
		case "ongoing" -> {
			events =
				eventRepository.findByEndDateAfter(LocalDateTime.now());
		}
		case "end" -> {
			events =
				eventRepository.findByEndDateBefore(LocalDateTime.now());
		}
		case "drawn" -> {
			events =
				eventRepository.findByDrawDateBefore(LocalDateTime.now());
		}
		default -> throw new CustomException(ErrorCode.BAD_REQUEST);
		}

		log.info(String.valueOf(events));

		if (Objects.equals(findDto.getSortedBy(), "endDate")) {

			events.sort((o1, o2) -> {
				if (o1.getEndDate().isBefore(o2.getEndDate())) {
					return -1;
				} else if (o1.getEndDate().equals(o2.getEndDate())) {
					return 0;
				}
				return 1;
			});
		}

		List<EventDto> eventDtos =
			events.stream()
				.map(event -> modelMapper.map(event, EventDto.class))
				.collect(Collectors.toList());

		log.info(String.valueOf(eventDtos));
		return eventDtos;
	}


	@Override
	public List<EventContentsImageDto> findEventContentsImages(Long eventId) {
		List<EventContentsImage> images =
			eventContentsImageRepository.findByEventId(eventId);

		List<EventContentsImageDto> imageDtos =
			images.stream()
				.map(image -> modelMapper.map(image, EventContentsImageDto.class))
				.collect(Collectors.toList());

		return imageDtos;
	}

}
