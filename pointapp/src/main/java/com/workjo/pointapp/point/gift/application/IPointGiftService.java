package com.workjo.pointapp.point.gift.application;

import com.workjo.pointapp.point.common.application.IPointService;
import com.workjo.pointapp.point.gift.dto.*;

import java.util.List;
import java.util.UUID;

public interface IPointGiftService {


    public void findReceivedUnrepliedGifts(GetReceivedPointGiftsDto dto, IPointService pointService);

    public void givePointGift(CreatePointGiftDto giftCreateDto, IPointService pointService);

    public void replyPointGift(ReplyPointGiftDto giftReplyDto, IPointService pointService);

    public PointGiftEntityDto updatePointGift(PointGiftEntityDto dto);

    public PointGiftEntityDto savePointGift(PointGiftEntityDto dto);
}