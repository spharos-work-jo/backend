package com.workjo.pointapp.point.gift.vo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetReceivedPointGiftsInfoRes {
    private List<PointGiftInfoRes> result;
}
