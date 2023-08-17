package com.workjo.pointapp.point.presentation;

import com.workjo.pointapp.point.application.PointService;
import com.workjo.pointapp.point.domain.Point;
import com.workjo.pointapp.point.dto.PointAddDto;
import com.workjo.pointapp.point.dto.PointGetDto;
import com.workjo.pointapp.point.vo.PointIn;
import com.workjo.pointapp.point.vo.PointOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class PointController {

    private final PointService pointService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/point/new")
    void addPoint(@RequestBody PointIn pointIn) {
        log.info("INPUT Object Data is : {}", pointIn);
        PointAddDto pointAddDto = PointAddDto.builder()
                .pointType(pointIn.getPointType())
                .point(pointIn.getPoint())
                .used(pointIn.getUsed())
                .loginId(pointIn.getLoginId())
                .build();
        pointService.createPoint(pointAddDto);
    }

    @GetMapping("/point")
    public List<PointOut> getPointByUser(@RequestHeader String token) {
        if (token ==null){
            return null;
        }
        String userId = jwtTokenProvider.getLoginId(token);
        List<PointGetDto> pointListByUser = pointService.getPointByUser(userId);
        List<PointOut> pointOutList = pointListByUser.stream().map(pointGetDto -> {
            return PointOut.builder()
                    .pointType(pointGetDto.getPointType())
                    .point(pointGetDto.getPoint())
                    .used(pointGetDto.getUsed())
                    .build();
        }).toList();
        log.info("OUTPUT pointOutList is : {}", pointOutList);
        return pointOutList;
    }

}
