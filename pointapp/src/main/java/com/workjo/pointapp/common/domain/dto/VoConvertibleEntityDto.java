package com.workjo.pointapp.common.domain.dto;

import com.workjo.pointapp.config.ModelMapperBean;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;


@Slf4j
public abstract class VoConvertibleEntityDto
        <D extends VoConvertibleEntityDto<D, E, R>, E, R>
        implements IEntityConvertible<E>, IResponseVoConvertible<R> {

    private final ModelMapper modelMapper = (new ModelMapperBean()).modelMapper();


    private final Class<D> dtoType;
    private final ConvertInfo<E> entity;
    private final ConvertInfo<R> response;


    //todo 리플렉션 변수에 constructor 지정하면 변수 넣을 수 있게 수정
    // entity,vo(Value) 어노테이션으로도 Entity,Vo의 생성자 지정할 수 있도록 수정

    @Override
    public final E toEntity() {
        E entity = modelMapper.map(dtoType.cast(this), this.entity.type());
        additionalEntityDataConvert(entity);
        return entity;
    }

    protected abstract void additionalEntityDataConvert(E entity);


    @Override
    public final R toResponseVo() {
        R responseVo =
                modelMapper.map(dtoType.cast(this), response.type());

        additionalResVoDataConvert(responseVo);

        return responseVo;
    }

    protected abstract void additionalResVoDataConvert(R responseVo);


    protected VoConvertibleEntityDto(Class<D> dtoType, ConvertInfo<E> entity, ConvertInfo<R> response) {
        this.dtoType = dtoType;
        this.entity = entity;
        this.response = response;
    }

}