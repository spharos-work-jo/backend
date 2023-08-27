package com.workjo.pointapp.point.vo.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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
