package com.workjo.pointapp.point.common.application;

import com.workjo.pointapp.point.common.dto.CreatePointDto;
import com.workjo.pointapp.point.common.dto.PointEntityDto;

import java.util.UUID;

public interface IPointService {

    PointEntityDto addPoint(CreatePointDto createDto);//test


    public PointEntityDto findPointById(Long pointId);


    public int getTotalPoint(UUID userUuid);

    public PointEntityDto saveTotalRenewedPoint(CreatePointDto createDto);

    public PointEntityDto saveTotalNotRenewedPoint(CreatePointDto createDto);
}