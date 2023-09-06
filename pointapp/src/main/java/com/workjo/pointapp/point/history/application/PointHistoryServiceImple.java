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
    public List<PointEntityDto> getPointHistoryOfUser(GetPointHistoryDto historyDto) {

        List<Point> pointList = this.getPointHistory(historyDto);

        if (pointList == null) {
            return new ArrayList<>();
        }
        log.info(String.valueOf(pointList));

        List<PointEntityDto> pointEntitiesDto;
        pointEntitiesDto = pointList.stream()
                .map(point -> modelMapper.map(point, PointEntityDto.class))
                .collect(Collectors.toList());

        log.info(String.valueOf(pointEntitiesDto));

        return pointEntitiesDto;
    }


    private List<Point> getPointHistory(GetPointHistoryDto dto) {

        return pointRepository.
                findByUserUuidAndPointTypeInAndRegDateBetweenOrderByRegDateDesc
                        (dto.getUserUuid(), dto.getPointTypesToSearch(), dto.getHistoryStartDate(), dto.getHistoryEndDate());
    }
}
