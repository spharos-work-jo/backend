package com.workjo.pointapp.point;

import com.workjo.pointapp.auth.AuthService;
import com.workjo.pointapp.auth.AuthUtils;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.point.common.service.pointpolicy.IPointPolicy;
import com.workjo.pointapp.point.common.vo.response.PointEntityRes;
import com.workjo.pointapp.point.earn.vo.PointEarnRes;
import com.workjo.pointapp.point.gift.domain.PointGiftStatus;
import com.workjo.pointapp.point.common.domain.PointType;
import com.workjo.pointapp.point.common.dto.CreatePointDto;
import com.workjo.pointapp.point.common.dto.PointEntityDto;
import com.workjo.pointapp.point.gift.dto.*;
import com.workjo.pointapp.point.gift.vo.request.ReplyPointGiftReq;
import com.workjo.pointapp.point.gift.vo.response.GetReceivedPointGiftsInfoRes;
import com.workjo.pointapp.point.gift.vo.response.GivePointGiftRes;
import com.workjo.pointapp.point.gift.vo.response.PointGiftInfoRes;
import com.workjo.pointapp.point.history.dto.GetPointHistoryDto;
import com.workjo.pointapp.point.earn.dto.EarnPointDto;
import com.workjo.pointapp.point.gift.vo.request.GivePointGiftReq;
import com.workjo.pointapp.point.history.vo.response.GetPointHistoryRes;
import com.workjo.pointapp.point.earn.vo.PointEarnReq;
import com.workjo.pointapp.point.history.vo.request.PointHistoryReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/point")
public class PointController {

    private final IPointService pointService;
    private final IPointPolicy pointPolicy;
    private final ModelMapper modelMapper;
    private final AuthService authService;

//todo 포인트 선물 전체 조회는 서비스단에 pointHistory 메서드로 기능 구현하기
//todo 미수락 받은 포인트 조회는 포인트 적립 테이블에 서칭하도록 따로 구현

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

        pointService.givePointGift(createDto);

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
        pointService.replyPointGift(replyDto);

        return ApiResponse.ofSuccess(null);
    }


    @PostMapping("/addtest/{point}")//todo 테스트용 코드
    public ApiResponse addPoint(Authentication auth, @PathVariable int point) {
        CreatePointDto createDto = new CreatePointDto(
                AuthUtils.getCurrentUserUUID(auth),
                point,
                PointType.ETC,
                "테스트"
        );

        return ApiResponse.ofSuccess(modelMapper.map(
                pointService.addPoint(createDto), PointEntityRes.class
        ));
    }


    @GetMapping("/gifts/receiveds/unreplieds")
    public ApiResponse<GetReceivedPointGiftsInfoRes> getUnrepliedReceivedGifts(Authentication auth) {
        GetReceivedPointGiftsDto dto = new GetReceivedPointGiftsDto(AuthUtils.getCurrentUserUUID(auth));
        pointService.findReceivedGifts(dto);

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


    @PostMapping("/new")
    public ApiResponse<PointEarnRes> earnPoint
            (@RequestBody PointEarnReq request) {

        //todo bill db 연결하기
        //find bill from db,
        // check if already saved point from bill
        // else get paidPrice from bill
        EarnPointDto dto = new EarnPointDto();
        modelMapper.map(request, dto);
        dto.setUserUuid(UUID.fromString(request.getUserUuid()));
        pointService.earnPoint(dto);

        PointEarnRes response = new PointEarnRes(dto);
        return ApiResponse.ofSuccess(response);
        //todo 어차피 dto 받이서 responseVo 생성해서 ApiResponse 생성해줄거라면, 다형적으로 dto랑 responseVo.class 인자로 받아서 안에서 modelMapper로 자동처리 해주는것도 구현하고 싶다....
        //todo dto super abstract class에 dto의 선언타입.class를 변수로 받아서 들고있고, 이를 이용해 AbstractDtoClass에서 dto가변인자 또는 dto 리스트를 받아 다형적으로 vo생성 메서드 구현

    }


    @PostMapping("/history")
    public ApiResponse<GetPointHistoryRes> getPointHistory
            (
                    @RequestBody PointHistoryReq request,
                    Authentication auth
            ) {
        // todo save_use 고려해서 리팩토링
        // dto 생성
        GetPointHistoryDto dto = new GetPointHistoryDto(request.getPointTypeCode());
        modelMapper.map(request, dto);
        dto.setHistoryStartDate(request.getHistoryStartDate().atStartOfDay());
        dto.setHistoryEndDate(request.getHistoryEndDate().atTime(LocalTime.MAX));
        dto.setUserUuid(AuthUtils.getCurrentUserUUID(auth));
        log.info(String.format("%s %s", request.getHistoryStartDate().toString(), request.getHistoryEndDate().toString()));
        log.info(dto.toString());

        List<PointEntityDto> pointEntityDtoList = pointService.getPointHistoryOfUser(dto);
        List<PointEntityRes> pointVoList = pointEntityDtoList.stream().map(
                pointEntityDto -> {
                    PointEntityRes pointEntityRes = new PointEntityRes();
                    modelMapper.map(pointEntityDto, pointEntityRes);
                    return pointEntityRes;
                }
        ).collect(Collectors.toList());


        return ApiResponse.ofSuccess(new GetPointHistoryRes(pointVoList));
    }

}
