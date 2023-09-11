package com.workjo.pointapp.event.apply.presentation;

import com.workjo.pointapp.auth.AuthUtils;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.event.apply.application.IEventApplyService;
import com.workjo.pointapp.event.apply.dto.EventApplyDto;
import com.workjo.pointapp.event.common.application.IEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/events/apply")
public class EventApplyController {

    private final IEventApplyService eventApplyService;


    @PostMapping
    public ApiResponse applyEvent
            (
                    @RequestBody Long eventId,
                    Authentication auth
            ) {

        EventApplyDto eventApplyDto = new EventApplyDto();
        eventApplyDto.setEventId(eventId);
        eventApplyDto.setUserUuid (AuthUtils.getCurrentUserUUID(auth));

        eventApplyService.applyEvent(eventApplyDto);


        return ApiResponse.ofSuccess(null);
    }

}
