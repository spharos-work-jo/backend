package com.workjo.pointapp.point.common.domain;

import com.workjo.pointapp.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PointType implements BaseEnum<String, String> {
    EVENT("E", "이벤트"),
    GIFT("G", "선물"),
    COUPON("C", "전환"),
    EARN("N", "적립"),
    USE("U","사용"),
    EXPIRE("X", "만료"),
    ETC("T", "기타");

    private String code;
    private String value;


    public static PointType ofCode(String code) {//todo 지우고 valueof사용
        PointType type;
        switch (code) {
            case "E" -> type = PointType.EVENT;
            case "G" -> type = PointType.GIFT;
            case "C" -> type = PointType.COUPON;
            case "B" -> type = PointType.EARN;
            default -> type = PointType.ETC;
        }

        return type;
    }

}
