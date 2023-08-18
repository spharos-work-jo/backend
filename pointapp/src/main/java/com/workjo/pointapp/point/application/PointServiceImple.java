package com.workjo.pointapp.point.application;

import com.workjo.pointapp.point.domain.Point;
import com.workjo.pointapp.point.domain.PointType;
import com.workjo.pointapp.point.domain.PointTypeConverter;
import com.workjo.pointapp.point.dto.PointGetDto;
import com.workjo.pointapp.point.infrastructure.PointRepository;
import com.workjo.pointapp.user.domain.User;
import com.workjo.pointapp.user.infrastructure.UserRepository;
import jakarta.persistence.Convert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointServiceImple implements PointService {

    private final PointRepository pointRepository;
    private final UserRepository userRepository;

//    @Override
//    @Convert(converter = PointTypeConverter.class)
//    public void createPoint(PointAddDto pointAddDto) {
//
//        User foundUser = userRepository.findByLoginId(pointAddDto.getLoginId());
//        log.info("user is : {}" , foundUser);
//        if(foundUser == null){
//            log.info("user is null");
//            return;
//        }
//
//        PointType pointType = new PointTypeConverter().convertToEntityAttribute(pointAddDto.getPointType());
//
//        //todo TotalPoint 계산
//
//        pointRepository.save(Point.builder()
//                .point(pointAddDto.getPoint())
//                .totalPoint(pointAddDto.getPoint())
//                .pointType(pointType)
//                .user(foundUser)
//                .used(pointAddDto.getUsed())
//                .build());
//    }


    @Override
    @Convert(converter = PointTypeConverter.class)
    public List<PointGetDto> getPointHistory(UUID uuid) {
        List<Point> pointList = pointRepository.findByUuid(uuid);
        List<PointGetDto> pointGetDtoList = pointList.stream().map(point -> {
                    PointType pointType = new PointTypeConverter().convertToEntityAttribute(point.getPointType().getCode());
                    return PointGetDto.builder()
                            .pointType(pointType.getValue())
                            .point(point.getPoint())
                            .used(point.getUsed())
                            .build();
                }
        ).collect(Collectors.toList());
        log.info("pointList is : {}", pointList);
        return pointGetDtoList;
    }
}
