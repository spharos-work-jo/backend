package com.workjo.pointapp.point.history.dto;

import com.workjo.pointapp.point.common.domain.PointType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetPointHistoryDto {
    private LocalDateTime historyStartDate;
    private LocalDateTime historyEndDate;
    private PointType pointType;
    private int saveUse;
    private UUID userUuid;

    //todo pointService.pointHistory 결과값도 여기 저장하도록 수정

    public GetPointHistoryDto(String pointTypeCode) {
        this.pointType = PointType.ofCode(pointTypeCode);
    }
}
