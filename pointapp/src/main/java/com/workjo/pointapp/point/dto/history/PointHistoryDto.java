package com.workjo.pointapp.point.dto.history;

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

    //todo pointService.pointHistory 결과값도 여기 저장하도록 수정

    public PointHistoryDto(String pointTypeCode) {
        this.pointType = PointType.ofCode(pointTypeCode);
    }
}
