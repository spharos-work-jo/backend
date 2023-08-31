package com.workjo.pointapp.point.dto.gift;

import com.workjo.pointapp.point.domain.PointGift;
import com.workjo.pointapp.point.domain.PointGiftStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointGiftCreateDto {
    private int point;
    //    private int pointPassword;
    private String toUserName;
    private PointGiftEntityDto pointGiftEntityDto;




}
