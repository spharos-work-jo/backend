package com.workjo.pointapp.point.common.application;

import com.workjo.pointapp.point.common.dto.CreatePointDto;
import com.workjo.pointapp.point.common.dto.PointEntityDto;

import java.util.UUID;

public interface IPointService {

    PointEntityDto addPoint(CreatePointDto createDto);//test

    PointEntityDto findPointById(Long pointId);

    int getTotalPoint(UUID userUuid);

    PointEntityDto saveTotalRenewedPoint(CreatePointDto createDto);

    PointEntityDto saveTotalNotRenewedPoint(CreatePointDto createDto);

//    void convertPointToUsable(Long unusablePointId, Long lastPointId);
}