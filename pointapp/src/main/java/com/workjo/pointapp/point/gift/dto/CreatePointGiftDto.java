package com.workjo.pointapp.point.gift.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePointGiftDto {
    private int point;
    //    private int pointPassword;
    private String toUserName;
    private PointGiftEntityDto pointGiftEntityDto;

}
