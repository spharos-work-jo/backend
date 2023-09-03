package com.workjo.pointapp.point.earn.vo;

import com.workjo.pointapp.point.earn.dto.EarnPointDto;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class PointEarnRes {
    private boolean isSucceeded;

    public PointEarnRes(EarnPointDto dto){
        this.isSucceeded = dto.getIsSucceeded();
    }
}
