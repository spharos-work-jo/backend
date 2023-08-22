package com.workjo.pointapp.point.dto;

import com.workjo.pointapp.point.domain.PointType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PointAddDto {

    private Long id;
    private UUID userUuid;
    private int total_point;
    private int point;
    private LocalDateTime regDate;
    private PointType pointType;
    private String title;

}
