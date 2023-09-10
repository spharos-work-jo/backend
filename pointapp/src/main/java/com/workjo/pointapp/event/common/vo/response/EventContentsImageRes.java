package com.workjo.pointapp.event.common.vo.response;


import lombok.Value;


@SuppressWarnings("RedundantModifiersValueLombok")
@Value
public class EventContentsImageRes {
    private Long id;
    private String imageUrl;
    private Long eventId;
}
