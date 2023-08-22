package com.workjo.pointapp.point.domain;

import com.workjo.pointapp.common.BaseEnum;
import lombok.Getter;

@Getter
public enum PointType implements BaseEnum {
    EVENT("E", "이벤트"),
    GIFT("G", "선물"),
    COUPON("C", "쿠폰"),
    ETC("T", "기타");

    private String code;
    private String value;

    PointType(String code, String value) {
        this.code = code;
        this.value = value;
    }

}
