package com.workjo.pointapp.point.common.domain;

import com.workjo.pointapp.common.AbstractBaseEnumConverter;
import com.workjo.pointapp.common.BaseEnum;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PointTypeConverter extends AbstractBaseEnumConverter<PointType, String, String> {
    public PointTypeConverter() {
        super(PointType.class);
    }

}

