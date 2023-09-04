package com.workjo.pointapp.point.gift;

import com.workjo.pointapp.auth.AuthUtils;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.point.common.application.IPointService;
import com.workjo.pointapp.point.gift.application.IPointGiftService;
import com.workjo.pointapp.point.gift.domain.PointGiftStatus;
import com.workjo.pointapp.point.gift.dto.CreatePointGiftDto;
import com.workjo.pointapp.point.gift.dto.GetReceivedPointGiftsDto;
import com.workjo.pointapp.point.gift.dto.PointGiftEntityDto;
import com.workjo.pointapp.point.gift.dto.ReplyPointGiftDto;
import com.workjo.pointapp.point.gift.vo.request.GivePointGiftReq;
import com.workjo.pointapp.point.gift.vo.request.ReplyPointGiftReq;
import com.workjo.pointapp.point.gift.vo.response.GetReceivedPointGiftsInfoRes;
import com.workjo.pointapp.point.gift.vo.response.GivePointGiftRes;
import com.workjo.pointapp.point.gift.vo.response.PointGiftInfoRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/point/gifts")
public class PointGiftController {

    private final IPointService pointService;
    private final ModelMapper modelMapper;
    private IPointGiftService pointGiftService;


    @PostMapping("/gifts/give")
    public ApiResponse<GivePointGiftRes> givePointGift
            (
                    @RequestBody GivePointGiftReq request,
                    Authentication auth
            ) {
        //todo 포인트 패스워드 고려해 리팩토링
        UUID fromUserUuid = AuthUtils.getCurrentUserUUID(auth);
        UUID toUserUuid = UUID.fromString(request.getToUserUuid());
        if (fromUserUuid.equals(toUserUuid) || request.getPoint() <= 0) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }

        PointGiftEntityDto giftEntityDto = modelMapper.map(request, PointGiftEntityDto.class);
        giftEntityDto.setFromUserUuid(fromUserUuid);
        giftEntityDto.setToUserUuid(toUserUuid);
        CreatePointGiftDto createDto = modelMapper.map(request, CreatePointGiftDto.class);
        createDto.setPointGiftEntityDto(giftEntityDto);

        pointGiftService.givePointGift(createDto, pointService);

        return ApiResponse.ofSuccess(null);
    }


    @PatchMapping("/gifts/{pointGiftId}/{giftReply}")
    public ApiResponse replyPointGift
            (
                    @PathVariable("pointGiftId") Long pointGiftId,
                    @PathVariable("giftReply") String giftReply,
                    Authentication auth,
                    @RequestBody ReplyPointGiftReq request
            ) {
        if (!giftReply.equals("ACCEPT") && !giftReply.equals("REJECT")) {
            throw new CustomException(ErrorCode.INVALID_POINT_REPLY_TYPE);
        }

        ReplyPointGiftDto replyDto = modelMapper.map(request, ReplyPointGiftDto.class);
        PointGiftEntityDto entityDto = modelMapper.map(request, PointGiftEntityDto.class);
        replyDto.setPointGiftEntityDto(entityDto);

        entityDto.setGiftStatus(PointGiftStatus.valueOf(giftReply));
        entityDto.setToUserUuid(UUID.fromString(request.getToUserUuid()));
        entityDto.setFromUserUuid(UUID.fromString(request.getFromUserUuid()));

        log.info(replyDto.toString());
        pointGiftService.replyPointGift(replyDto,pointService);

        return ApiResponse.ofSuccess(null);
    }


    @GetMapping("/gifts/receiveds/unreplieds")
    public ApiResponse<GetReceivedPointGiftsInfoRes> getUnrepliedReceivedGifts(Authentication auth) {
        GetReceivedPointGiftsDto dto = new GetReceivedPointGiftsDto(AuthUtils.getCurrentUserUUID(auth));
        pointGiftService.findReceivedUnrepliedGifts(dto, pointService);

        List<PointGiftInfoRes> giftInfoVoList = dto.getResult().stream().map(
                giftInfoDto -> {
                    PointGiftInfoRes giftInfoRes = PointGiftInfoRes.builder()
                            .point(giftInfoDto.getPoint()).build();

                    modelMapper.map(giftInfoDto.getPointGiftEntityDto(), giftInfoRes);

                    return giftInfoRes;
                }
        ).collect(Collectors.toList());

        GetReceivedPointGiftsInfoRes response =
                new GetReceivedPointGiftsInfoRes(giftInfoVoList);

        return ApiResponse.ofSuccess(response);
    }

}
