package com.workjo.pointapp.point.domain;

import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class PointTypeConverter implements AttributeConverter<PointType, String> {

    @Override
    public String convertToDatabaseColumn(PointType attribute) {
        return attribute.getCode();
    }

    @Override
    public PointType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(PointType.class).stream()
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 코드입니다."));
    }

}
