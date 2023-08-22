package com.workjo.pointapp.point.application;

import com.workjo.pointapp.point.domain.Point;
import com.workjo.pointapp.point.dto.PointAddDto;
import com.workjo.pointapp.point.dto.PointGetDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PointService {
    void createPoint(PointAddDto pointAddDto);

    List<PointGetDto> getPointHistoryOfUser(UUID uuid, LocalDateTime historyStartDate, LocalDateTime historyEndDate);
}
