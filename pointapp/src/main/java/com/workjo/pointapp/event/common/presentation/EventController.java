package com.workjo.pointapp.event.common.presentation;

import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.event.apply.application.IEventApplyService;
import com.workjo.pointapp.event.common.application.IEventService;
import com.workjo.pointapp.event.common.dto.EventContentsImageDto;
import com.workjo.pointapp.event.common.dto.EventDto;
import com.workjo.pointapp.event.common.dto.FindEventByStatusDto;
import com.workjo.pointapp.event.common.vo.response.EventContentsImageRes;
import com.workjo.pointapp.event.common.vo.response.EventEntityRes;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/events")
public class EventController {

    private final IEventService eventService;
    private final ModelMapper modelMapper;


    @GetMapping
    public ApiResponse<EventEntityRes> findById
            (
                    @RequestParam Long id
            ) {

        EventDto eventDto =
                eventService.findById(id);


        return ApiResponse.ofSuccess(eventDto.toResponseVo());
    }


    @GetMapping("/{id}/contents")
    public ApiResponse<List<EventContentsImageRes>> findEventContentsImages
            (
                    @RequestParam Long eventId
            ) {
        List<EventContentsImageDto> imageDtos =
                eventService.findEventContentsImages(eventId);

        List<EventContentsImageRes> imageResList =
                imageDtos.stream()
                        .map(EventContentsImageDto::toResponseVo)
                        .collect(Collectors.toList());

        return ApiResponse.ofSuccess(imageResList);
    }


    @GetMapping("/{eventStatus}")

    public ApiResponse<List<EventEntityRes>> findByEventStatus
            (
                    @PathVariable String eventStatus,
                    @RequestParam(required = false) String sortedBy
            ) {

        FindEventByStatusDto findDto =
                new FindEventByStatusDto(eventStatus);

        List<EventDto> eventsDto =
                eventService.findByStatus(findDto);

        List<EventEntityRes> eventsVo =
                eventsDto.stream()
                        .map(EventDto::toResponseVo)
                        .collect(Collectors.toList());

        return ApiResponse.ofSuccess(eventsVo);
    }


}
