package com.workjo.pointapp.point.history.application;

import com.workjo.pointapp.point.common.domain.Point;
import com.workjo.pointapp.point.common.dto.PointEntityDto;
import com.workjo.pointapp.point.common.infrastructure.IPointRepository;
import com.workjo.pointapp.point.history.dto.GetPointHistoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointHistoryServiceImple implements IPointHistoryService {
    private final ModelMapper modelMapper;
    private final IPointRepository pointRepository;

    @Override
    public List<PointEntityDto> getPointHistoryOfUser(GetPointHistoryDto dto) {
        List<Point> pointList = this.getPointHistory(dto);
        if (pointList == null) {
            return new ArrayList<PointEntityDto>();
        }

        List<PointEntityDto> pointEntityDtoList = pointList.stream().map(
                point -> {
                    PointEntityDto pointEntityDto = new PointEntityDto();
                    modelMapper.map(point, pointEntityDto);
                    return pointEntityDto;
                }
        ).collect(Collectors.toList());

        return pointEntityDtoList;
    }


    private List<Point> getPointHistory(GetPointHistoryDto dto)
    {
        return pointRepository.
                findByUserUuidAndPointTypeAndRegDateBetweenOrderByRegDateDesc
                        (dto.getUserUuid(), dto.getPointType(), dto.getHistoryStartDate(), dto.getHistoryEndDate());
    }
}
