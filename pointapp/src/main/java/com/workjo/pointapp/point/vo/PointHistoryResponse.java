package com.workjo.pointapp.point.vo;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class PointHistoryResponse {

    private final int totalPoint;
    private final int addedDuringPeriod;
    private final int usedDuringPeriod;
    private final List<PointResponse> pointList;


    public PointHistoryResponse(List<PointResponse> pointList) {
        this.totalPoint = pointList.get(0).getTotalPoint();
        this.pointList = pointList;

        int added = 0, used = 0;
        for (PointResponse point : pointList) {
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
