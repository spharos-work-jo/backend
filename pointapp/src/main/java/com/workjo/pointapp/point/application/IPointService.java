package com.workjo.pointapp.point.application;

import com.workjo.pointapp.point.dto.PointEarnDto;
import com.workjo.pointapp.point.dto.PointDto;
import com.workjo.pointapp.point.dto.PointHistoryDto;

import java.util.List;
import java.util.UUID;

public interface IPointService {
    //     void createPoint(PointCreateDto pointAddDto);

    List<PointDto> getPointHistoryOfUser(PointHistoryDto requestDto);

    void earnPoint(PointEarnDto pointEarnDto);
}
