package com.workjo.pointapp.point.vo;

import com.workjo.pointapp.point.domain.PointType;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class PointHistoryRequest {
    private LocalDateTime historyStartDate;
    private LocalDateTime historyEndDate;
    private PointType pointType;
    private int saveUse;
}
