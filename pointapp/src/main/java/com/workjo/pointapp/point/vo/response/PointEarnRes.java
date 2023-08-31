package com.workjo.pointapp.point.vo.response;

import com.workjo.pointapp.point.dto.earn.PointEarnDto;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class PointEarnRes {
    private boolean isSucceeded;

    public PointEarnRes(PointEarnDto dto){
        this.isSucceeded = dto.getIsSucceeded();
    }
}
