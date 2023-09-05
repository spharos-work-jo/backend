package com.workjo.pointapp.event.dto;

import com.workjo.pointapp.common.domain.dto.VoConvertibleEntityDto;
import com.workjo.pointapp.common.domain.dto.ConvertInfo;
import com.workjo.pointapp.event.domain.Event;
import com.workjo.pointapp.event.domain.EventType;
import com.workjo.pointapp.event.vo.response.EventEntityRes;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@ToString
@Setter
public class EventEntityDto extends VoConvertibleEntityDto<EventEntityDto, Event, EventEntityRes> {

    private Long id;
    private Long partnerId;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private EventType type;
    private String imageUrl;


    @Builder
    public EventEntityDto() {
        super(
                EventEntityDto.class,
                new ConvertInfo<Event>(Event.class, null),
                new ConvertInfo<EventEntityRes>(EventEntityRes.class, null)
        );
    }


    @Override
    protected void additionalEntityDataConvert(Event entity) {
    }

    @Override
    protected void additionalResVoDataConvert(EventEntityRes responseVo) {
    }
}
