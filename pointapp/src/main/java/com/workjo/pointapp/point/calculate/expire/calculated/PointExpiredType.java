package com.workjo.pointapp.point.calculate.expire.calculated;

import com.workjo.pointapp.banner.BannerContentsType;
import com.workjo.pointapp.common.AbstractBaseEnumConverter;
import com.workjo.pointapp.common.BaseEnum;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PointExpiredType implements BaseEnum<String, String> {
    DECREASED_TOTAL_POINT("D"),
    LACK_TOTAL_POINT("L");

    private String code;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return name();
    }


    @Converter(autoApply = true)
    public static class PointExpiredTypeConverter extends AbstractBaseEnumConverter<PointExpiredType, String, String> {
        public PointExpiredTypeConverter() {
            super(PointExpiredType.class);
        }
    }
}
