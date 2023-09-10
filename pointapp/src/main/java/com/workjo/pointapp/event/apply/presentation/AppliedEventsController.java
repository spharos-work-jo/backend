package com.workjo.pointapp.event.apply.presentation;

import com.workjo.pointapp.auth.AuthUtils;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.event.apply.application.IEventApplyService;
import com.workjo.pointapp.event.apply.domain.EventApplyStatus;
import com.workjo.pointapp.event.apply.dto.EventApplyDto;
import com.workjo.pointapp.event.apply.dto.FindAppliedEventsByStatusDto;
import com.workjo.pointapp.event.apply.vo.response.EventApplyRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/events/applieds")
public class AppliedEventsController {
    private final IEventApplyService eventApplyService;

    @GetMapping
    public ApiResponse<EventApplyRes> findById
            (
                    @RequestParam Long id,
                    Authentication auth
            ) {

        EventApplyDto eventApplyDto = eventApplyService.findById(id);

        if (eventApplyDto.getUserUuid() != AuthUtils.getCurrentUserUUID(auth)) {
            throw new CustomException(ErrorCode.NOT_FOUND_RESOURCE);
        }

        return ApiResponse.ofSuccess
                (eventApplyDto.toResponseVo());
    }

    @GetMapping("/{applyStatus}")
    public ApiResponse<List<EventApplyRes>> findByStatus
            (
                    @PathVariable(required = false) String applyStatus,
                    Authentication auth
            ) {

        List<EventApplyDto> eventApplyDtos;

        if (applyStatus == null) {

            eventApplyDtos =
                    eventApplyService.findByUserUuid
                            (AuthUtils.getCurrentUserUUID(auth));
        } else {
            try {

                FindAppliedEventsByStatusDto findDto =
                        FindAppliedEventsByStatusDto.builder()
                                .userUuid
                                        (AuthUtils.getCurrentUserUUID(auth))
                                .status
                                        (EventApplyStatus.valueOf(applyStatus))
                                .build();


                eventApplyDtos =
                        eventApplyService.findByUserUuidAndStatus(findDto);

            } catch (Exception e) {
                throw new CustomException(ErrorCode.BAD_REQUEST);
            }
        }


        List<EventApplyRes> eventApplyResList =
                eventApplyDtos.stream()
                        .map(EventApplyDto::toResponseVo)
                        .collect(Collectors.toList());

        return ApiResponse.ofSuccess(eventApplyResList);
    }


}
