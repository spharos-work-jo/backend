package com.workjo.pointapp.point.gift.domain;

import com.workjo.pointapp.common.AbstractBaseEnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PointGiftStatusConverter extends AbstractBaseEnumConverter<PointGiftStatus, Character, String> {
    public PointGiftStatusConverter() {
        super(PointGiftStatus.class);
    }
}
