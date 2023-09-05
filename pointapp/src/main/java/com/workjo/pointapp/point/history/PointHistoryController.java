package com.workjo.pointapp.point.history;

import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.point.common.domain.PointType;
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

        GetPointHistoryDto dto = GetPointHistoryDto.builder()
                .pointTypesToSearch
                        (mapValueToEnum(request.getPointTypesToSearch()))
                .historyStartDate
                        (request.getHistoryStartDate().atStartOfDay())
                .historyEndDate
                        (request.getHistoryEndDate().atTime(LocalTime.MAX))
                .build();

//        log.info(String.format("%s %s", request.getHistoryStartDate().toString(), request.getHistoryEndDate().toString()));
//        log.info(dto.toString());

        // dto로 찾은 포인트 내역 request Vo로 변환
        List<PointEntityDto> pointHistoryDto =
                pointHistoryService.getPointHistoryOfUser(dto);

        List<PointEntityRes> pointHistoryVo = pointHistoryDto.stream()
                .map(pointEntityDto ->
                        modelMapper.map(pointEntityDto, PointEntityRes.class))
                .collect(Collectors.toList());


        return ApiResponse.ofSuccess
                (GetPointHistoryRes.of(pointHistoryVo));
    }


    private List<PointType> mapValueToEnum(List<String> pointTypeValues) {

        return pointTypeValues.stream()
                .map(PointType::valueOf)
                .collect(Collectors.toList());
    }
}
