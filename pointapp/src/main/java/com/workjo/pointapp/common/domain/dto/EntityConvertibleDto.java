package com.workjo.pointapp.common.domain.dto;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

public abstract class EntityConvertibleDto<E> implements IEntityConvertible<E> {
    private final ModelMapper modelMapper;
    private final Class<E> entityType;

//    public final E toEntity() {
//        Class<E> entityType;
//        Class<E> entity = modelMapper.map(this, entityType.class);
//    }

    protected EntityConvertibleDto(@Autowired ModelMapper modelMapper, Class<E> entityType) {
        this.modelMapper = modelMapper;
        this.entityType = entityType;
    }


    protected abstract void additionalDataConvert();
}
