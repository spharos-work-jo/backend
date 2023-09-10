package com.workjo.pointapp.event.apply.vo.response;

import com.workjo.pointapp.event.apply.domain.EventApplyStatus;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@SuppressWarnings("RedundantModifiersValueLombok")
@Value
public class EventApplyRes {
    private Long id;
    private EventApplyStatus status;
    private UUID userUuid;
    private Long eventId;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

}
