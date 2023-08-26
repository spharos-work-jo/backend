package com.workjo.pointapp.point.domain;

import com.workjo.pointapp.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.QTypeContributor;

@Getter
@AllArgsConstructor
public enum PointType implements BaseEnum<String, String> {
    EVENT("E", "이벤트"),
    GIFT("G", "선물"),
    COUPON("C", "전환"),
    BILL("B", "계산"),
    ETC("T", "기타");

    private String code;
    private String value;

    public static PointType ofCode(String code) {
        PointType type;
        switch (code) {
            case "E" -> type = PointType.EVENT;
            case "G" -> type = PointType.GIFT;
            case "C" -> type = PointType.COUPON;
            case "B" -> type = PointType.BILL;
            default -> type = PointType.ETC;
        }

        return type;
    }

}
