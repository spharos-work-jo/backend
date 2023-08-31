package com.workjo.pointapp.point.application;

import com.workjo.pointapp.point.dto.common.PointCreateDto;
import com.workjo.pointapp.point.dto.common.PointEntityDto;
import com.workjo.pointapp.point.dto.gift.PointGiftEntityDto;
import com.workjo.pointapp.point.dto.gift.PointGiftGetUnrepliedDto;
import com.workjo.pointapp.point.dto.history.PointHistoryDto;
import com.workjo.pointapp.point.dto.earn.PointEarnDto;
import com.workjo.pointapp.point.dto.gift.PointGiftCreateDto;
import com.workjo.pointapp.point.dto.gift.PointGiftReplyDto;

import java.util.List;

public interface IPointService {
    //     void createPoint(PointCreateDto pointAddDto);

    List<PointEntityDto> getPointHistoryOfUser(PointHistoryDto requestDto);

    void earnPoint(PointEarnDto pointEarnDto);

    void givePointGift(PointGiftCreateDto dto);

    void replyPointGift(PointGiftReplyDto replyDto);

    PointEntityDto addPoint(PointCreateDto createDto);

//    List<PointGiftEntityDto> getUnrepliedGifts(PointGiftGetUnrepliedDto dto);
}
