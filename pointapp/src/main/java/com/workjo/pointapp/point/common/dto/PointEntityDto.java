package com.workjo.pointapp.point.common.dto;

import com.workjo.pointapp.point.common.domain.PointType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PointEntityDto {

    private Long id;
    private UUID userUuid;
    private int totalPoint;
    private int point;
    private LocalDateTime regDate;
    private PointType pointType;
    private String title;

}
