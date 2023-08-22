package com.workjo.pointapp.point.vo;

import com.workjo.pointapp.point.domain.PointType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PointIn {

    private Long id;
    private UUID userUuid;
    private int total_point;
    private int point;
    private LocalDateTime regDate;
    private PointType pointType;
    private String title;

}
