package com.workjo.pointapp.point.history.vo.response;

import com.workjo.pointapp.point.common.vo.response.PointEntityRes;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class GetPointHistoryRes {

    private final int totalPoint;
    private final int addedDuringPeriod;
    private final int usedDuringPeriod;
    private final List<PointEntityRes> pointList;


    public GetPointHistoryRes(List<PointEntityRes> pointList) {
        if (pointList.isEmpty()) {
            this.addedDuringPeriod = 0;
            this.usedDuringPeriod = 0;
            this.totalPoint = 0;
            this.pointList = new ArrayList<>();

            return;
        }


        int added = 0, used = 0;
        for (PointEntityRes point : pointList) {
            if (point.getPoint() > 0) {
                added += point.getPoint();
            } else {
                used += -point.getPoint();
            }
        }
        this.totalPoint = pointList.get(0).getTotalPoint();
        this.pointList = pointList;
        this.addedDuringPeriod = added;
        this.usedDuringPeriod = used;
    }

}