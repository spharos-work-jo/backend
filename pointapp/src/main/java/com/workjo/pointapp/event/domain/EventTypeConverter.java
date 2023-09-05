package com.workjo.pointapp.event.domain;

import com.workjo.pointapp.common.AbstractBaseEnumConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EventTypeConverter extends AbstractBaseEnumConverter<EventType, String,String> {
    public EventTypeConverter() {
        super(EventType.class);
    }
}
