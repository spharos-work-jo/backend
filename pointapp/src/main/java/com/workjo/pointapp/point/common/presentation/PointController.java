package com.workjo.pointapp.point.common.presentation;

import com.workjo.pointapp.auth.AuthService;
import com.workjo.pointapp.auth.AuthUtils;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.point.common.application.IPointService;
import com.workjo.pointapp.point.common.vo.response.PointEntityRes;
import com.workjo.pointapp.point.common.domain.PointType;
import com.workjo.pointapp.point.common.dto.CreatePointDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/point")
public class PointController {

    private final IPointService pointService;
    private final ModelMapper modelMapper;
    private final AuthService authService;

//todo 포인트 선물 전체 조회는 서비스단에 pointHistory 메서드로 기능 구현하기

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


}
