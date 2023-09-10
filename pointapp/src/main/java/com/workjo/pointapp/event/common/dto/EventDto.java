package com.workjo.pointapp.event.common.dto;

import com.workjo.pointapp.common.domain.dto.VoConvertibleEntityDto;
import com.workjo.pointapp.common.domain.dto.ConvertInfo;
import com.workjo.pointapp.event.common.domain.Event;
import com.workjo.pointapp.event.common.vo.response.EventEntityRes;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Setter
public class EventDto extends VoConvertibleEntityDto<EventDto, Event, EventEntityRes> {

    private Long id;
    private Long partnerId;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime rewardDate;
    private String thumbnailUrl;

    @Builder
    public EventDto() {
        super(
                EventDto.class,
                new ConvertInfo<Event>(Event.class, null),
                new ConvertInfo<EventEntityRes>(EventEntityRes.class, null)
        );
    }

    @Builder
    public EventDto(Long id, Long partnerId, String name, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime rewardDate, String thumbnailUrl) {
        this();
        this.id = id;
        this.partnerId = partnerId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rewardDate = rewardDate;
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    protected void additionalEntityDataConvert(Event entity) {
    }

    @Override
    protected void additionalResVoDataConvert(EventEntityRes responseVo) {
    }
}
