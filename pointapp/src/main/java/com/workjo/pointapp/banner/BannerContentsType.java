package com.workjo.pointapp.banner;

import com.workjo.pointapp.common.AbstractBaseEnumConverter;
import com.workjo.pointapp.common.BaseEnum;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum BannerContentsType implements BaseEnum<String, String> {
    EVENT("E");

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
    public static class BannerContentsTypeConverter extends AbstractBaseEnumConverter<BannerContentsType, String, String> {

        public BannerContentsTypeConverter() {
            super(BannerContentsType.class);
        }
    }
}
