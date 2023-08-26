package com.workjo.pointapp.point.dto;

import com.workjo.pointapp.point.domain.PointType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PointHistoryDto {
    private LocalDateTime historyStartDate;
    private LocalDateTime historyEndDate;
    private PointType pointType;
    private int saveUse;
    private UUID userUuid;

    public PointHistoryDto(String pointTypeCode) {
        this.pointType = PointType.ofCode(pointTypeCode);
    }
}
