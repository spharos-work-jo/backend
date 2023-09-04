package com.workjo.pointapp.point.history;

import com.workjo.pointapp.auth.AuthUtils;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.point.common.application.IPointService;
import com.workjo.pointapp.point.common.dto.PointEntityDto;
import com.workjo.pointapp.point.common.vo.response.PointEntityRes;
import com.workjo.pointapp.point.history.application.IPointHistoryService;
import com.workjo.pointapp.point.history.dto.GetPointHistoryDto;
import com.workjo.pointapp.point.history.vo.request.PointHistoryReq;
import com.workjo.pointapp.point.history.vo.response.GetPointHistoryRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/point/history")
public class PointHistoryController {
    private final ModelMapper modelMapper;
    private final IPointHistoryService pointHistoryService;

    @PostMapping("")
    public ApiResponse<GetPointHistoryRes> getPointHistory
            (
                    @RequestBody PointHistoryReq request,
                    Authentication auth
            ) {
        // todo save_use 고려해서 리팩토링
        // todo 선물 보낸 뒤 거절되어 돌아오면 전체 적립에 카운트되는지 확인
        // dto 생성
        GetPointHistoryDto dto = new GetPointHistoryDto(request.getPointTypeCode());
        modelMapper.map(request, dto);
        dto.setHistoryStartDate(request.getHistoryStartDate().atStartOfDay());
        dto.setHistoryEndDate(request.getHistoryEndDate().atTime(LocalTime.MAX));
        dto.setUserUuid(AuthUtils.getCurrentUserUUID(auth));
        log.info(String.format("%s %s", request.getHistoryStartDate().toString(), request.getHistoryEndDate().toString()));
        log.info(dto.toString());

        // dto로 찾은 포인트 내역 request Vo로 변환
        List<PointEntityDto> pointEntityDtoList = pointHistoryService.getPointHistoryOfUser(dto);
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
