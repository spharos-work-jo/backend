package com.workjo.pointapp.point.earn.application;

import com.workjo.pointapp.point.common.application.IPointService;
import com.workjo.pointapp.point.earn.dto.EarnPointDto;

public interface IPointEarnService {
    void earnPoint(EarnPointDto earnDto, IPointService pointService);
}
