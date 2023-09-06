package com.workjo.pointapp.event.presentation;

import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.event.application.EventServiceImple;
import com.workjo.pointapp.event.domain.EventType;
import com.workjo.pointapp.event.dto.EventEntityDto;
import com.workjo.pointapp.event.dto.GetEventsDto;
import com.workjo.pointapp.event.vo.response.EventEntityRes;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventServiceImple eventService;

    @GetMapping("/ongoing")
    public ApiResponse<List<EventEntityRes>> getOngoingEvents
            (
                    @RequestParam(name = "sortedBy") List<String> sortedBy,
                    @RequestParam(name = "typesToSearch") List<String> typesToSearch
            ) {

        GetEventsDto dto =
                GetEventsDto.builder()
                        .eventTypesToSearch
                                (mapEventType(typesToSearch))
                        .minEndDate
                                (LocalDateTime.now())
                        .maxEndDate
                                (LocalDateTime.MAX)
                        .build();

        List<EventEntityDto> eventsDto =
                eventService.getEvents(dto);

        List<EventEntityRes> eventsVo =
                eventsDto.stream()
                        .map(EventEntityDto::toResponseVo)
                        .collect(Collectors.toList());

        return ApiResponse.ofSuccess(eventsVo);
    }

    private List<EventType> mapEventType(List<String> typesToSearch) {
        return typesToSearch.stream()
                .map(EventType::valueOf)
                .collect(Collectors.toList());
    }

}
