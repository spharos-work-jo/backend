package com.workjo.pointapp.event.vo.response;

import com.workjo.pointapp.event.domain.EventType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@ToString
public class EventEntityRes {
    private Long id;
    private Long partnerId;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private EventType type;
    private String imageUrl;
}
