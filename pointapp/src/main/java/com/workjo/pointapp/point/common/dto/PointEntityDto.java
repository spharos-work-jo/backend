package com.workjo.pointapp.point.common.dto;

import com.workjo.pointapp.point.common.domain.PointType;
import jakarta.persistence.Column;
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
    private PointType type;
    private String title;

//    private boolean isUsable;

}
