package com.workjo.pointapp.point.history.vo.response;

import com.workjo.pointapp.point.common.vo.response.PointEntityRes;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@SuppressWarnings("RedundantModifiersValueLombok")
public class GetPointHistoryRes {

    private final int totalPoint;
    private final List<PointEntityRes> pointList;

    public static GetPointHistoryRes of(List<PointEntityRes> pointList) {
        return new GetPointHistoryRes(pointList);
    }

    public GetPointHistoryRes(List<PointEntityRes> pointList) {
        this.pointList = pointList;
        if (pointList.isEmpty())
            totalPoint = 0;
        else
            totalPoint = pointList.get(0).getTotalPoint();
    }
}