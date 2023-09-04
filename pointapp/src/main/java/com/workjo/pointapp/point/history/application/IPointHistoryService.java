package com.workjo.pointapp.point.history.application;

import com.workjo.pointapp.point.common.dto.PointEntityDto;
import com.workjo.pointapp.point.history.dto.GetPointHistoryDto;

import java.util.List;

public interface IPointHistoryService {
    List<PointEntityDto> getPointHistoryOfUser(GetPointHistoryDto dto);
}
