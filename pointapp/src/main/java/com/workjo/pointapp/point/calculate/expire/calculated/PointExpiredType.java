package com.workjo.pointapp.point.calculate.expire.calculated;

import com.workjo.pointapp.banner.BannerContentsType;
import com.workjo.pointapp.common.AbstractBaseEnumConverter;
import com.workjo.pointapp.common.BaseEnum;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PointExpiredType implements BaseEnum<String, String> {
    DECREASED_TOTAL_POINT("D", "DECREASED_TOTAL_POINT"),
    LACK_TOTAL_POINT("L", "LACK_TOTAL_POINT");

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


    @Converter(autoApply = true)
    public static class PointExpiredTypeConverter extends AbstractBaseEnumConverter<PointExpiredType, String, String> {
        public PointExpiredTypeConverter() {
            super(PointExpiredType.class);
        }
    }
}
