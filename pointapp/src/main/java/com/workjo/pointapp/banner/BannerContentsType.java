package com.workjo.pointapp.banner;

import com.workjo.pointapp.common.AbstractBaseEnumConverter;
import com.workjo.pointapp.common.BaseEnum;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum BannerContentsType implements BaseEnum<String, String> {
    EVENT("E","EVENT");

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
    public static class BannerContentsTypeConverter extends AbstractBaseEnumConverter<BannerContentsType, String, String> {

        public BannerContentsTypeConverter() {
            super(BannerContentsType.class);
        }
    }
}
