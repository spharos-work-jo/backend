package com.workjo.pointapp.point;

import com.workjo.pointapp.point.common.dto.CreatePointDto;
import com.workjo.pointapp.point.common.dto.PointEntityDto;
import com.workjo.pointapp.point.gift.dto.GetReceivedPointGiftsDto;
import com.workjo.pointapp.point.gift.dto.ReplyPointGiftDto;
import com.workjo.pointapp.point.history.dto.GetPointHistoryDto;
import com.workjo.pointapp.point.earn.dto.EarnPointDto;
import com.workjo.pointapp.point.gift.dto.CreatePointGiftDto;

import java.util.List;

public interface IPointService {
    //     void createPoint(CreatePointDto pointAddDto);

    List<PointEntityDto> getPointHistoryOfUser(GetPointHistoryDto requestDto);

    void earnPoint(EarnPointDto pointEarnDto);

    void givePointGift(CreatePointGiftDto dto);

    void replyPointGift(ReplyPointGiftDto replyDto);

    PointEntityDto addPoint(CreatePointDto createDto);

    void findReceivedGifts(GetReceivedPointGiftsDto dto);
}
