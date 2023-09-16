package com.workjo.pointapp.point.earn.presentation;

import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.point.common.application.IPointService;
import com.workjo.pointapp.point.earn.application.IPointEarnService;
import com.workjo.pointapp.point.earn.dto.EarnPointDto;
import com.workjo.pointapp.point.earn.vo.PointEarnReq;
import com.workjo.pointapp.point.earn.vo.PointEarnRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/point/earn")
public class PointEarnController {
    private final IPointService pointService;
    private final ModelMapper modelMapper;
    private final IPointEarnService pointEarnService;

    @PostMapping
    public ApiResponse<PointEarnRes> earnPoint
            (@RequestBody PointEarnReq request) {
        if (request.getPaidPrice() < 0) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }


        EarnPointDto dto = new EarnPointDto();
        modelMapper.map(request, dto);
        dto.setUserUuid(UUID.fromString(request.getUserUuid()));
        pointEarnService.earnPoint(dto, pointService);

        PointEarnRes response = new PointEarnRes(dto);
        return ApiResponse.ofSuccess(response);

    }
}
