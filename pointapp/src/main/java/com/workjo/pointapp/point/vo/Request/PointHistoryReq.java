package com.workjo.pointapp.point.vo.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.workjo.pointapp.point.domain.PointType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PointHistoryReq {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate historyStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate historyEndDate;
    private String pointTypeCode;
    private int saveUse;
}
