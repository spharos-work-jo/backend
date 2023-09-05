package com.workjo.pointapp.point.history.vo.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PointHistoryReq {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate historyStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate historyEndDate;
    private List<String> pointTypesToSearch;
}
