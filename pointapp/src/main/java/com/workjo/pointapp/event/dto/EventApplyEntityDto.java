package com.workjo.pointapp.event.dto;

import com.workjo.pointapp.common.domain.dto.ConvertInfo;
import com.workjo.pointapp.common.domain.dto.VoConvertibleEntityDto;
import com.workjo.pointapp.event.domain.EventApply;
import com.workjo.pointapp.event.vo.response.EventApplyEntityRes;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
@Setter
public class EventApplyEntityDto
        extends VoConvertibleEntityDto<EventApplyEntityDto, EventApply, EventApplyEntityRes> {

    private Long id;
    private Long eventId;
    private LocalDateTime regDate;
    private Boolean isRewarded;
    private UUID userUuid;
    private LocalDateTime updateDate;


    @Builder
    public EventApplyEntityDto() {
        super(
                EventApplyEntityDto.class,
                new ConvertInfo<EventApply>(EventApply.class, null),
                new ConvertInfo<EventApplyEntityRes>(EventApplyEntityRes.class, null)
        );
    }


    @Override
    protected void additionalEntityDataConvert(EventApply entity) {
    }

    @Override
    protected void additionalResVoDataConvert(EventApplyEntityRes responseVo) {
    }
}
