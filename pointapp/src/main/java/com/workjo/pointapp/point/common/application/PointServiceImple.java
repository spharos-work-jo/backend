package com.workjo.pointapp.point.common.application;

import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.point.common.domain.observable.INotUsablePointObservable;
import com.workjo.pointapp.point.common.domain.observable.IUsablePointObservable;
import com.workjo.pointapp.point.common.domain.*;
import com.workjo.pointapp.point.common.dto.CreatePointDto;
import com.workjo.pointapp.point.common.dto.PointEntityDto;
import com.workjo.pointapp.point.common.infrastructure.IPointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointServiceImple implements IPointService {

    private final ModelMapper modelMapper;
    private final IPointRepository pointRepository;

    private final List<INotUsablePointObservable> notUsablePointObservers;
    private final List<IUsablePointObservable> usablePointObservers;


    @Override //test
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
    public int getTotalPoint(UUID userUuid) {
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
        Point point = Point.builder()
                .totalPoint(totalAfterCreate)
                .build();
        log.info("total after gift : " + totalAfterCreate);
        modelMapper.map(createDto, point);
//        log.info("\n\n\n mylog");
//        log.info(point.getTitle());
//        log.info(point.getUserUuid().toString());
//        log.info(String.format("%d", point.getPoint()));
//        log.info(String.format("%d", point.getTotalPoint()));
//        log.info(point.getPointType().getValue());
        Point savedPoint = this.savePoint(point);
        PointEntityDto savedPointDto = modelMapper.map(savedPoint, PointEntityDto.class);

        if (savedPoint.getPoint() > 0) {
            usablePointObservers.
                    forEach(observer ->
                            observer.observeUsablePointIncreased(savedPointDto));
        } else if (savedPoint.getType() == PointType.EXPIRE) {

        }//todo observer 지우고 point 사용량을 기록하는 ㅌ테이블 하나 새로 만들기

        return savedPointDto;
    }


    @Override
    public PointEntityDto saveTotalNotRenewedPoint(CreatePointDto createDto) {

        Point pointToSave =
                Point.builder()
                        .totalPoint(this.getTotalPoint(createDto.getUserUuid()))
                        .build();
        modelMapper.map(createDto, pointToSave);

        Point savedEntity = this.savePoint(pointToSave);
        PointEntityDto savedPointDto = modelMapper.map(savedEntity, PointEntityDto.class);

        notUsablePointObservers
                .forEach(observer -> {
                    observer.observeUnusablePointSaved(savedPointDto);
                });

        return savedPointDto;
    }


    private Point savePoint(Point point) {
        Point savedPoint=null;
        try {
            savedPoint = pointRepository.save(point);
//        if (savedPoint == null) {
//            throw new CustomException(ErrorCode.ENTITY_SAVE_FAILED);
//        }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }

        return savedPoint;
    }

}