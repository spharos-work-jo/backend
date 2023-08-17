package com.workjo.pointapp.point.application;

import com.workjo.pointapp.point.domain.Point;
import com.workjo.pointapp.point.dto.PointAddDto;
import com.workjo.pointapp.point.dto.PointGetDto;

import java.util.List;

public interface PointService {
    void createPoint(PointAddDto pointAddDto);
    List<PointGetDto> getPointByUser(Long userId);
    List<Point> getAllPoint();
}
