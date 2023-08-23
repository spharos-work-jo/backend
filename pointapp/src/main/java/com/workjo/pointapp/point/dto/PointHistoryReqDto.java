package com.workjo.pointapp.point.dto;

import com.workjo.pointapp.point.domain.PointType;
import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

import java.time.LocalDateTime;

@Data
public class PointHistoryReqDto {
    private LocalDateTime historyStartDate;
    private LocalDateTime historyEndDate;
    private PointType pointType;
    private int saveUse;
}
