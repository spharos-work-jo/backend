package com.workjo.pointapp.event.domain;

import com.workjo.pointapp.common.BaseEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EventType implements BaseEnum<String, String> {
    NO_REWARD("N", "no_reward"),
    POINT_REWARD("P", "point_reward"),
    COUPON_REWARD("C", "coupon_reward");

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
