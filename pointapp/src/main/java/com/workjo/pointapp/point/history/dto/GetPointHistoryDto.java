package com.workjo.pointapp.point.history.dto;

import com.workjo.pointapp.point.common.domain.PointType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class GetPointHistoryDto {
    private List<PointType> pointTypesToSearch;
    private UUID userUuid;
    private LocalDateTime historyStartDate;
    private LocalDateTime historyEndDate;

    public boolean isTypeToSearch(PointType type) {
        return this.pointTypesToSearch.contains(type);
    }


    //todo pointService.pointHistory 결과값도 여기 저장하도록 수정

}
