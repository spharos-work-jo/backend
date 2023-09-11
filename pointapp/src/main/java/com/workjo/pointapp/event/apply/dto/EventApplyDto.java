package com.workjo.pointapp.event.apply.dto;

import com.workjo.pointapp.common.domain.dto.ConvertInfo;
import com.workjo.pointapp.common.domain.dto.VoConvertibleEntityDto;
import com.workjo.pointapp.event.apply.domain.EventApply;
import com.workjo.pointapp.event.apply.domain.EventApplyStatus;
import com.workjo.pointapp.event.apply.vo.response.EventApplyRes;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
@Setter
public class EventApplyDto
        extends VoConvertibleEntityDto<EventApplyDto, EventApply, EventApplyRes> {

    private Long id;
    private EventApplyStatus status;
    private UUID userUuid;
    private Long eventId;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;


    @Builder
    public EventApplyDto() {
        super(
                EventApplyDto.class,
                new ConvertInfo<EventApply>(EventApply.class, null),
                new ConvertInfo<EventApplyRes>(EventApplyRes.class, null)
        );
    }


    @Override
    protected void additionalEntityDataConvert(EventApply entity) {
    }

    @Override
    protected void additionalResVoDataConvert(EventApplyRes responseVo) {
    }
}
