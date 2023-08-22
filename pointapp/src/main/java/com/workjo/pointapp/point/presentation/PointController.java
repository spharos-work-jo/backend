package com.workjo.pointapp.point.presentation;

import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.point.application.PointService;
import com.workjo.pointapp.point.domain.Point;
import com.workjo.pointapp.point.dto.PointAddDto;
import com.workjo.pointapp.point.dto.PointGetDto;
import com.workjo.pointapp.point.vo.PointHistoryData;
import com.workjo.pointapp.point.vo.PointIn;
import com.workjo.pointapp.point.vo.PointOut;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class PointController {

    private final PointService pointService;
//    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/point/new")
    void addPoint(@RequestBody int storeId, @RequestBody int receiptId, @RequestBody UUID userUuid) {


    }

    @GetMapping("/point")
    public ApiResponse getPointHistory(
            @RequestHeader String token,
            @RequestBody LocalDateTime historyStartDate,
            @RequestBody LocalDateTime historyEndDate
    ) {
        if (token == null) {
            return null;
        }
//        UUID uuid = jwtTokenProvider.getUuid(token);
        UUID uuid = UUID.randomUUID();//for test


        List<PointGetDto> pointListByUser = pointService.getPointHistoryOfUser(uuid, historyStartDate, historyEndDate);
        ModelMapper modelMapper = new ModelMapper();

        List<PointOut> pointOutList = pointListByUser.stream().map(pointGetDto -> {
            PointOut pointOut = new PointOut();
            modelMapper.map(pointGetDto, pointOut);
            return pointOut;
        }).collect(Collectors.toList());


        return ApiResponse.ofSuccess(new PointHistoryData(pointOutList));
    }

}
