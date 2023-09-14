package com.workjo.pointapp.point.common.dto;

import com.workjo.pointapp.point.common.domain.PointType;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePointDto {
    private UUID userUuid;
    private int point;
    private PointType type;
    private String title;
}
