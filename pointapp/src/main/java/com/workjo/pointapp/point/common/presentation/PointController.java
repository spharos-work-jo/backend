package com.workjo.pointapp.point.common.presentation;


import com.workjo.pointapp.auth.AuthService;
import com.workjo.pointapp.auth.AuthUtils;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.point.common.application.IPointService;
import com.workjo.pointapp.point.common.domain.PointType;
import com.workjo.pointapp.point.common.dto.CreatePointDto;
import com.workjo.pointapp.point.common.vo.response.PointEntityRes;
import com.workjo.pointapp.point.common.vo.response.PointInfoRes;
import com.workjo.pointapp.point.common.vo.response.PointSimpleInfoRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/point")
public class PointController {

    private final IPointService pointService;
    private final ModelMapper modelMapper;
    private final AuthService authService;

//todo 포인트 선물 전체 조회는 서비스단에 pointHistory 메서드로 기능 구현하기

    @PostMapping("/addtest/{pointAmount}")
    public ApiResponse addPoint(Authentication auth, @PathVariable("pointAmount") int pointAmount) {

        log.info(String.valueOf(auth));
        log.info(String.format("%d\n", pointAmount));
        CreatePointDto createDto = new CreatePointDto(
                AuthUtils.getCurrentUserUUID(auth),
                pointAmount,
                PointType.ETC,
                "테스트"
        );

        return ApiResponse.ofSuccess(modelMapper.map(
                pointService.addPoint(createDto), PointEntityRes.class
        ));
    }


    @GetMapping("/simple-info")
    public ApiResponse<PointSimpleInfoRes> getPointSimpleInfo(Authentication authentication) {
        Integer totalPoint = pointService.getTotalPoint(AuthUtils.getCurrentUserUUID(authentication));

        return ApiResponse.ofSuccess(
                PointSimpleInfoRes.builder()
                        .usableTotalPoint(totalPoint)
                        .build()
        );
    }


    @GetMapping("/info")
    public ApiResponse<PointInfoRes> getPointInfo(Authentication authentication) {
        Integer totalPoint = pointService.getTotalPoint(AuthUtils.getCurrentUserUUID(authentication));
        LocalDate monthFirstDay = LocalDate.now().withDayOfMonth(1);
        LocalDate expDate1 = monthFirstDay.plusMonths(1); // todo 내부 로직으로 변경
        LocalDate expDate2 = monthFirstDay.plusMonths(2);
        return ApiResponse.ofSuccess(
                PointInfoRes.builder()
                        .usableTotalPoint(totalPoint)
                        .expDate1(expDate1)
                        .expDate2(expDate2)
                        .build()
        );
    }

}
