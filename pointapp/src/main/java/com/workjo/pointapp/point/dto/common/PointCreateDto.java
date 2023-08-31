package com.workjo.pointapp.point.dto.common;

import com.workjo.pointapp.point.domain.PointType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointCreateDto {
    private UUID userUuid;
    private int point;
    private PointType pointType;
    private String title;
}
