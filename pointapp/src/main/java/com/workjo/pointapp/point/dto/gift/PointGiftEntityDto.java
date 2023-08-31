package com.workjo.pointapp.point.dto.gift;

import com.workjo.pointapp.common.domain.dto.IEntityConvertible;
import com.workjo.pointapp.config.security.ApplicationConfig;
import com.workjo.pointapp.point.domain.PointGift;
import com.workjo.pointapp.point.domain.PointGiftStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointGiftEntityDto {
    private Long id;
    private String message;
    private PointGiftStatus giftStatus = PointGiftStatus.WAIT;
    private Long sentPointId;
    private Long receivedPointId;
    private UUID toUserUuid;
    private UUID fromUserUuid;

    //todo entityConvertibleDto 리팩토링
//    private Class<PointGift> entityType = PointGift.class;
    @Autowired
    private ModelMapper modelMapper;


    public PointGift toEntity() {
        return modelMapper.map(this, PointGift.class);
    }
}
