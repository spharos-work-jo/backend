package com.workjo.pointapp.event.apply.domain;

import com.workjo.pointapp.common.AbstractBaseEnumConverter;
import com.workjo.pointapp.common.BaseEnum;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EventApplyStatus implements BaseEnum<String, String> {

    NOT_DRAWN("N", "NOT_DRAWN"),
    FAIL("F", "FAIL"),
    WIN("W", "WIN");


    private String code;
    private String value;


    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getValue() {
        return null;
    }


    @Converter(autoApply = true)
    public static class StatusConverter extends AbstractBaseEnumConverter<EventApplyStatus, String, String> {
        public StatusConverter(Class<EventApplyStatus> clazz) {
            super(clazz);
        }
    }
}
