package com.workjo.pointapp.point.presentation;

import com.workjo.pointapp.auth.AuthService;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.point.application.pointpolicy.IPointPolicy;
import com.workjo.pointapp.point.application.IPointService;
import com.workjo.pointapp.point.dto.PointDto;
import com.workjo.pointapp.point.dto.PointEarnDto;
import com.workjo.pointapp.point.dto.PointHistoryDto;
import com.workjo.pointapp.point.vo.request.PointGiftReq;
import com.workjo.pointapp.point.vo.response.PointGiftRes;
import com.workjo.pointapp.point.vo.request.PointEarnReq;
import com.workjo.pointapp.point.vo.request.PointHistoryReq;
import com.workjo.pointapp.point.vo.response.PointEarnRes;
import com.workjo.pointapp.point.vo.response.PointHistoryRes;
import com.workjo.pointapp.point.vo.response.PointEntityRes;
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


    @

    PostMapping("/gift")
    public ApiResponse<PointGiftRes>(
    @RequestBody
    PointGiftReq request)

    {
//todo 포인트 선물 시 이름/전화번호로 사용자의 이름/로그인 id 조회, 보내려는 사용자 맞는지 확인 후 선물할 포인트 량과 포인트 비밀번호 입력해서 보냄, 이를 고려해서 api 상세하게 수정 후 구현할것
    }


    @PostMapping("/new")
    public ApiResponse<PointEarnRes> earnPoint
            (
                    @RequestBody PointEarnReq request
            ) {
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

        List<PointDto> pointDtoList = pointService.getPointHistoryOfUser(dto);
        List<PointEntityRes> pointVoList = pointDtoList.stream().map(
                pointDto -> {
                    PointEntityRes pointEntityRes = new PointEntityRes();
                    modelMapper.map(pointDto, pointEntityRes);
                    return pointEntityRes;
                }
        ).collect(Collectors.toList());


        return ApiResponse.ofSuccess(new PointHistoryRes(pointVoList));
    }

}
