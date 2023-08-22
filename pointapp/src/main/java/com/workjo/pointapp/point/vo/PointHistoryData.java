package com.workjo.pointapp.point.vo;

import lombok.Getter;

import java.util.List;

@Getter
public class PointHistoryData {

    private final int totalPoint;
    private final int addedDuringPeriod;
    private final int usedDuringPeriod;
    private final List<PointOut> pointList;


    public PointHistoryData(List<PointOut> pointList) {
        this.totalPoint = pointList.get(0).getTotalPoint();
        this.pointList = pointList;

        int added = 0, used = 0;
        for (PointOut point : pointList) {
            if (point.getPoint() > 0) {
                added += point.getPoint();
            } else {
                used += -point.getPoint();
            }
        }
        this.addedDuringPeriod = added;
        this.usedDuringPeriod = used;
    }

}
