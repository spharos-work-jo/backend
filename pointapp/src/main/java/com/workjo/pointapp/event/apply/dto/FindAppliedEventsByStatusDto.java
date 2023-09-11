package com.workjo.pointapp.event.apply.dto;


import com.workjo.pointapp.event.apply.domain.EventApplyStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@RequiredArgsConstructor
@Builder
public class FindAppliedEventsByStatusDto {

    private final UUID userUuid;
    private final EventApplyStatus status;
}
