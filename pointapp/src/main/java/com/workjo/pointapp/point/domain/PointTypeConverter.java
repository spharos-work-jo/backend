package com.workjo.pointapp.point.domain;

import com.workjo.pointapp.common.AbstractBaseEnumConverter;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class PointTypeConverter extends AbstractBaseEnumConverter {
    public PointTypeConverter(Class clazz) {
        super(clazz);
    }

    @Override
    public Object convertToDatabaseColumn(Object attribute) {
        return null;
    }
}

