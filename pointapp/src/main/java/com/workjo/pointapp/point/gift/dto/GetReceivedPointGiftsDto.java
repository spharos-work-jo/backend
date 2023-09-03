package com.workjo.pointapp.point.gift.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class GetReceivedPointGiftsDto {
    private final UUID userUuid;
    private List<PointGiftInfoDto> result;
}
