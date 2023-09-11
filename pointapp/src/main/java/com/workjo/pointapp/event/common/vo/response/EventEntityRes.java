package com.workjo.pointapp.event.common.vo.response;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Data
public class EventEntityRes {

    private Long id;

    private Long partnerId;

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime rewardDate;

    private String thumbnailUrl;

    //    private String status;
}
