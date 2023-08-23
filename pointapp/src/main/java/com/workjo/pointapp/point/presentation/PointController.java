package com.workjo.pointapp.point.presentation;

import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.point.application.pointpolicy.IPointPolicy;
import com.workjo.pointapp.point.application.IPointService;
import com.workjo.pointapp.point.dto.PointGetDto;
import com.workjo.pointapp.point.dto.PointHistoryReqDto;
import com.workjo.pointapp.point.vo.PointEarnRequest;
import com.workjo.pointapp.point.vo.PointHistoryRequest;
import com.workjo.pointapp.point.vo.PointHistoryResponse;
import com.workjo.pointapp.point.vo.PointResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
//    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/point/new")
    void earnPoint(@RequestParam PointEarnRequest request) {
        //find bill from db,
        // check if already saved point from bill
        // else get paidPrice from bill
        int pointAmount = pointPolicy.getPoint(request.getPaidPrice());
        pointService.

    }


    @GetMapping("/point")
    public ApiResponse getPointHistory(@RequestParam PointHistoryRequest request,
                                       @RequestHeader String token) {
        if (token == null) {
            return null;
        }
//        UUID uuid = jwtTokenProvider.getUuid(token);
        UUID uuid = UUID.randomUUID();// delete

        ModelMapper modelMapper = new ModelMapper();
        PointHistoryReqDto requestDto = new PointHistoryReqDto();
        modelMapper.map(request, requestDto);

        List<PointGetDto> pointListByUser = pointService.getPointHistoryOfUser(uuid, requestDto);

        List<PointResponse> pointOutList = pointListByUser.stream().map(pointGetDto -> {
            PointResponse pointOut = new PointResponse();
            modelMapper.map(pointGetDto, pointOut);
            return pointOut;
        }).collect(Collectors.toList());


        return ApiResponse.ofSuccess(new PointHistoryResponse(pointOutList));
    }

}
