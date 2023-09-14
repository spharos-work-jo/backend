package com.workjo.pointapp.point.common.domain;

import com.workjo.pointapp.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PointType implements BaseEnum<String, String> {
    EVENT_REWARD("ER", "EVENT_REWARD"),
    GIFT("G", "GIFT"),
    COUPON("C", "COUPON"),
    EARN("E", "EARN"),
    USE("U", "USE"),
    EXPIRE("EX", "EXPIRE"),
    ETC("ET", "ETC");

    private String code;
    private String value;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}
