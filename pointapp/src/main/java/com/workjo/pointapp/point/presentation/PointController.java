package com.workjo.pointapp.point.presentation;

import com.workjo.pointapp.auth.AuthService;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.point.application.pointpolicy.IPointPolicy;
import com.workjo.pointapp.point.application.IPointService;
import com.workjo.pointapp.point.dto.PointDto;
import com.workjo.pointapp.point.dto.PointEarnDto;
import com.workjo.pointapp.point.dto.PointHistoryDto;
import com.workjo.pointapp.point.vo.Request.PointEarnReq;
import com.workjo.pointapp.point.vo.Request.PointHistoryReq;
import com.workjo.pointapp.point.vo.Response.PointEarnRes;
import com.workjo.pointapp.point.vo.Response.PointHistoryRes;
import com.workjo.pointapp.point.vo.Response.PointEntityRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class PointController {

    private final IPointService pointService;
    private final IPointPolicy pointPolicy;
    private final ModelMapper modelMapper;
    private final AuthService authService;


    @PostMapping("/point/new")
    public ApiResponse<PointEarnRes> earnPoint
            (
                    @RequestParam PointEarnReq request
            ) {//todo bill db 연결하기
        //find bill from db,
        // check if already saved point from bill
        // else get paidPrice from bill
        PointEarnDto dto = new PointEarnDto();
        modelMapper.map(request, dto);
        pointService.earnPoint(dto);

        PointEarnRes response = new PointEarnRes();
        return ApiResponse.ofSuccess(response);
    }


    @GetMapping("/point")
    public ApiResponse<PointHistoryRes> getPointHistory
            (
                    @RequestParam PointHistoryReq request,
                    Authentication auth
            ) {


        PointHistoryDto dto = new PointHistoryDto();
        modelMapper.map(request, dto);
        UUID userUuid = authService.getCurrentUserUUID(auth);
        dto.setUserUuid(userUuid);

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

