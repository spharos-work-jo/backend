package com.workjo.pointapp.event.common.dto;

import com.workjo.pointapp.common.domain.dto.ConvertInfo;
import com.workjo.pointapp.common.domain.dto.VoConvertibleEntityDto;
import com.workjo.pointapp.event.common.domain.EventContentsImage;
import com.workjo.pointapp.event.common.vo.response.EventContentsImageRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Getter
@Setter
@Slf4j
public class EventContentsImageDto
        extends VoConvertibleEntityDto<EventContentsImageDto, EventContentsImage, EventContentsImageRes> {
    private Long id;
    private String imageUrl;
    private Long eventId;

    @Override
    protected void additionalEntityDataConvert(EventContentsImage entity) {

    }

    @Override
    protected void additionalResVoDataConvert(EventContentsImageRes responseVo) {

    }

    public EventContentsImageDto() {
        super(EventContentsImageDto.class,
                new ConvertInfo<>(EventContentsImage.class, null),
                new ConvertInfo<>(EventContentsImageRes.class, null));
    }
}
