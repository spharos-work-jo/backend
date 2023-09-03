package com.workjo.pointapp.common.domain.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class EntityConvertibleDto<D extends EntityConvertibleDto, E> implements IEntityConvertible<D> {
    private final ModelMapper modelMapper;
    private final Class<D> entityType;

//    public final D toEntity() {
//        Class<D> entityType;
//        Class<D> entity = modelMapper.map(this, entityType.class);
//    }

    protected EntityConvertibleDto(@Autowired ModelMapper modelMapper, Class<D> entityType) {
        this.modelMapper = modelMapper;
        this.entityType = entityType;
    }


    protected abstract void additionalDataConvert();
}
