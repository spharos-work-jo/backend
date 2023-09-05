package com.workjo.pointapp.point.common.domain;

import com.workjo.pointapp.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PointType implements BaseEnum<String, String> {
    EVENT("ER", "EVENT_REWARD"),
    GIFT("G", "GIFT"),
    COUPON("C", "COUPON"),
    EARN("E", "EARN"),
    USE("U", "USE"),
    EXPIRE("EX", "EXPIRE"),
    ETC("ET", "ETC");

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
