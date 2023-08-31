package com.workjo.pointapp.point.presentation;

import com.workjo.pointapp.auth.AuthService;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.point.application.pointpolicy.IPointPolicy;
import com.workjo.pointapp.point.application.IPointService;
import com.workjo.pointapp.point.domain.PointGift;
import com.workjo.pointapp.point.domain.PointGiftStatus;
import com.workjo.pointapp.point.domain.PointType;
import com.workjo.pointapp.point.dto.common.PointCreateDto;
import com.workjo.pointapp.point.dto.common.PointEntityDto;
import com.workjo.pointapp.point.dto.gift.PointGiftEntityDto;
import com.workjo.pointapp.point.dto.gift.PointGiftGetUnrepliedDto;
import com.workjo.pointapp.point.dto.history.PointHistoryDto;
import com.workjo.pointapp.point.dto.earn.PointEarnDto;
import com.workjo.pointapp.point.dto.gift.PointGiftCreateDto;
import com.workjo.pointapp.point.dto.gift.PointGiftReplyDto;
import com.workjo.pointapp.point.vo.request.PointGiftGiveReq;
import com.workjo.pointapp.point.vo.request.PointGiftReplyReq;
import com.workjo.pointapp.point.vo.response.*;
import com.workjo.pointapp.point.vo.request.PointEarnReq;
import com.workjo.pointapp.point.vo.request.PointHistoryReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.convert.EntityConverter;
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
    public ApiResponse<PointGiftGiveRes> givePointGift
            (
                    @RequestBody PointGiftGiveReq request,
                    Authentication auth
            ) {
        //todo 포인트 패스워드 고려해 리팩토링
        log.info(request.toString());

        PointGiftEntityDto giftEntityDto = modelMapper.map(request, PointGiftEntityDto.class);
        giftEntityDto.setFromUserUuid(authService.getCurrentUserUUID(auth));
        giftEntityDto.setToUserUuid(UUID.fromString(request.getToUserUuid()));
        PointGiftCreateDto createDto = modelMapper.map(request, PointGiftCreateDto.class);
        createDto.setPointGiftEntityDto(giftEntityDto);


        log.info(createDto.toString());

        pointService.givePointGift(createDto);

        return ApiResponse.ofSuccess(null);
    }


    @PatchMapping("/gifts/{pointGiftId}/{giftReply}")
    public ApiResponse replyPointGift
            (
                    @PathVariable("pointGiftId") Long pointGiftId,
                    @PathVariable("giftReply") String giftReply,
                    Authentication auth,
                    @RequestBody PointGiftReplyReq request
            ) {
        if (!giftReply.equals("ACCEPT") && !giftReply.equals("REJECT")) {
            throw new CustomException(ErrorCode.INVALID_POINT_REPLY_TYPE);
        }

        PointGiftReplyDto replyDto = modelMapper.map(request, PointGiftReplyDto.class);
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
        PointCreateDto createDto = new PointCreateDto(
                authService.getCurrentUserUUID(auth),
                point,
                PointType.ETC,
                "테스트"
        );

        return ApiResponse.ofSuccess(modelMapper.map(
                pointService.addPoint(createDto), PointEntityRes.class
        ));
    }


//    @GetMapping("/gifts/{userUuid}/receiveds/upreplieds")
//    public List<PointGiftEntityRes> getUnrepliedGifts(Authentication auth) {
//        PointGiftGetUnrepliedDto dto = PointGiftGetUnrepliedDto.builder()
//                .userUuid(authService.getCurrentUserUUID(auth))
//                .build();
//
//        return pointService.getUnrepliedGifts(dto).stream().map(
//                pointGiftEntityDto -> {
//                    return modelMapper.map(pointGiftEntityDto, PointGiftEntityRes.class);
//                }
//        ).collect(Collectors.toList());
//    }


    @PostMapping("/new")
    public ApiResponse<PointEarnRes> earnPoint
            (@RequestBody PointEarnReq request) {

        //todo bill db 연결하기
        //find bill from db,
        // check if already saved point from bill
        // else get paidPrice from bill
        PointEarnDto dto = new PointEarnDto();
        modelMapper.map(request, dto);
        dto.setUserUuid(UUID.fromString(request.getUserUuid()));
        pointService.earnPoint(dto);

        PointEarnRes response = new PointEarnRes(dto);
        return ApiResponse.ofSuccess(response);
        //todo 어차피 dto 받이서 responseVo 생성해서 ApiResponse 생성해줄거라면, 다형적으로 dto랑 responseVo.class 인자로 받아서 안에서 modelMapper로 자동처리 해주는것도 구현하고 싶다....
        //todo dto super abstract class에 dto의 선언타입.class를 변수로 받아서 들고있고, 이를 이용해 AbstractDtoClass에서 dto가변인자 또는 dto 리스트를 받아 다형적으로 vo생성 메서드 구현

    }


    @PostMapping("/history")
    public ApiResponse<PointHistoryRes> getPointHistory
            (
                    @RequestBody PointHistoryReq request,
                    Authentication auth
            ) {
        // todo save_use 고려해서 리팩토링
        // dto 생성
        PointHistoryDto dto = new PointHistoryDto(request.getPointTypeCode());
        modelMapper.map(request, dto);
        dto.setHistoryStartDate(request.getHistoryStartDate().atStartOfDay());
        dto.setHistoryEndDate(request.getHistoryEndDate().atTime(LocalTime.MAX));
        dto.setUserUuid(authService.getCurrentUserUUID(auth));
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


        return ApiResponse.ofSuccess(new PointHistoryRes(pointVoList));
    }

}
