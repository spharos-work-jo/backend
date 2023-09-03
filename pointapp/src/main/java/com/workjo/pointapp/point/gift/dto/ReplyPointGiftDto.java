package com.workjo.pointapp.point.gift.dto;

import lombok.Data;

@Data
public class ReplyPointGiftDto {
    private Integer point;
    private PointGiftEntityDto pointGiftEntityDto;

//    public static ReplyPointGiftDto ofRequest(ReplyPointGiftReq request) {
//        ReplyPointGiftDto dto = new ReplyPointGiftDto();
//        modelMapper.map(request, dto);
//        return
//    }

}
