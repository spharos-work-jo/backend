package com.workjo.pointapp.point.common.domain;

import com.workjo.pointapp.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PointType implements BaseEnum<String, String> {
    EVENT_REWARD("ER"),
    GIFT("G"),
    COUPON("C"),
    EARN("E"),
    USE("U"),
    EXPIRE("EX"),
    ETC("ET");

    private String code;


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return name();
    }
}
