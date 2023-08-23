package com.workjo.pointapp.point.application;

import com.workjo.pointapp.point.dto.PointEarnDto;
import com.workjo.pointapp.point.dto.PointGetDto;
import com.workjo.pointapp.point.dto.PointHistoryReqDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IPointService {
    //     void createPoint(PointCreateDto pointAddDto);

    List<PointGetDto> getPointHistoryOfUser(UUID uuid, PointHistoryReqDto requestDto );

    boolean earnPoint(PointEarnDto pointEarnDto);
}
