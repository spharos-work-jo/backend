package com.workjo.pointapp.point.gift.dto;

import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.point.gift.domain.PointGift;
import com.workjo.pointapp.point.gift.domain.PointGiftStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class PointGiftEntityDto {
    private Long id;
    private String message;
    @Builder.Default
    private PointGiftStatus giftStatus = PointGiftStatus.WAIT;
    private Long sentPointId;
    private Long resultPointId;
    private UUID toUserUuid;
    private UUID fromUserUuid;


    @Builder.Default
    private ModelMapper privateStrictModelMapper = (new ModelMapperBean()).privateStrictModelMapper();
//    private Class<PoiGif> responseType = PointGiftEntityRes.class;

    @Builder.Default
    private Class<PointGift> entityType = PointGift.class;

    public PointGift toEntity() {
        return privateStrictModelMapper.map(this, PointGift.class);
    }

//    public PointGiftEntityRes toResponse() {
//        return privateStrictModelMapper.map(this, responseType);
//    }

//    public void toResponse() {
//
//        Constructor annotatedConstructor= Arrays.stream(responseType.getConstructors()).filter(constructor -> {constructor.getAnnotation()})
//
////        responseType.getMethod()
//        try {
//            Constructor responseConstructor = responseType.getConstructor(allTypesOfFieldsInResponse);
//        } catch (NoSuchMethodException noSuchMethodException) {
//            log.info("response Vo 객체에 AllArgs");
//            noSuchMethodException.printStackTrace();
//        }
//    }
}
