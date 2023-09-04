package com.workjo.pointapp.point.common.application;

import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.point.common.infrastructure.IPointRepository;
import com.workjo.pointapp.point.common.domain.*;
import com.workjo.pointapp.point.common.dto.CreatePointDto;
import com.workjo.pointapp.point.common.dto.PointEntityDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointServiceImple implements IPointService {

    private final ModelMapper modelMapper;
    private final IPointRepository pointRepository;

    @Override //todo 테스트용 코드
    public PointEntityDto addPoint(CreatePointDto dto) {
        return modelMapper.map(
                this.saveTotalRenewedPoint(dto), PointEntityDto.class);
    }

    @Override
    public PointEntityDto findPointById(Long pointId) {
        Point foundPoint = pointRepository.findById(pointId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));

        return modelMapper.map(foundPoint, PointEntityDto.class);
    }

    @Override
    public int getTotalPoint(UUID userUuid) { //todo consider server cache
        Point point = pointRepository.findFirstByUserUuidOrderByRegDateDesc(userUuid)
                .orElse(null);
        if (point == null) {
            return 0;
        }

        return point.getTotalPoint();
    }

    @Override
    public PointEntityDto saveTotalRenewedPoint(CreatePointDto createDto) {
        int totalBeforeCreate = this.getTotalPoint(createDto.getUserUuid());
        log.info("total before gift : " + totalBeforeCreate);
        int totalAfterCreate = createDto.getPoint() + totalBeforeCreate;
        if (totalAfterCreate < 0) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        Point point = Point.builder().totalPoint(totalAfterCreate).build();
        log.info("total after gift : " + totalAfterCreate);

        modelMapper.map(createDto, point);
//        log.info("\n\n\n mylog");
//        log.info(point.getTitle());
//        log.info(point.getUserUuid().toString());
//        log.info(String.format("%d", point.getPoint()));
//        log.info(String.format("%d", point.getTotalPoint()));
//        log.info(point.getPointType().getValue());


        return modelMapper.map(
                this.savePoint(point), PointEntityDto.class);
    }

    @Override
    public PointEntityDto saveTotalNotRenewedPoint(CreatePointDto createDto) {
        Point pointToSave =
                Point.builder()
                        .totalPoint(this.getTotalPoint(createDto.getUserUuid()))
                        .build();
        modelMapper.map(createDto, pointToSave);

        Point savedEntity = this.savePoint(pointToSave);

        return modelMapper.map(savedEntity, PointEntityDto.class);

    }


    private Point savePoint(Point point) {
        Point savedPoint = pointRepository.save(point);
        if (savedPoint == null) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR/*todo POINT_SAVE_FAILED 에러코드 정의해서 쓰기*/);
        }
        return savedPoint;
    }

}