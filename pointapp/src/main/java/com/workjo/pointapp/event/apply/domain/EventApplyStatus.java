package com.workjo.pointapp.event.apply.domain;

import com.workjo.pointapp.common.AbstractBaseEnumConverter;
import com.workjo.pointapp.common.BaseEnum;
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
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }


    @jakarta.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractBaseEnumConverter<EventApplyStatus, String, String> {
        public Converter() {
            super(EventApplyStatus.class);
        }
    }
}
