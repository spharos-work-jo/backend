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
    private PointGiftStatus giftStatus = PointGiftStatus.WAIT;
    private Long sentPointId;
    private Long resultPointId;
    private UUID toUserUuid;
    private UUID fromUserUuid;

    //todo EntityAndRequestVoConvertibleDto 리팩토링
//    private Class<PointGift> entityType = PointGift.class;
    private ModelMapper privateStrictModelMapper = (new ModelMapperBean()).privateStrictModelMapper();
//    private Class<PoiGif> responseType = PointGiftEntityRes.class;
// todo 리팩터링 시 db에 entity정보 생성/변경과 관련된 dto는 entity dto로,
//     여러 db의 정보를 취합해 response에 전달하는 dto는 response dto로 만들어 convertible하게 만들기, 둘다되는 애도 만들기 -> entityDto: entity/entityResVo 모두 변환가능, response변환만 가능한 resDto도 만들기
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
