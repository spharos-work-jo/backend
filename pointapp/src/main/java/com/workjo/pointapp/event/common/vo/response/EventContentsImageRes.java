package com.workjo.pointapp.event.common.vo.response;


import lombok.Data;


@Data
public class EventContentsImageRes {
    private Long id;
    private String imageUrl;
    private Long eventId;
}
