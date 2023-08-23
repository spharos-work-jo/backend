package com.workjo.pointapp.point.application;

import com.workjo.pointapp.point.application.pointpolicy.IPointPolicy;
import com.workjo.pointapp.point.domain.Point;
import com.workjo.pointapp.point.domain.PointEarn;
import com.workjo.pointapp.point.domain.PointType;
import com.workjo.pointapp.point.domain.PointTypeConverter;
import com.workjo.pointapp.point.dto.PointCreateDto;
import com.workjo.pointapp.point.dto.PointEarnDto;
import com.workjo.pointapp.point.dto.PointGetDto;
import com.workjo.pointapp.point.dto.PointHistoryReqDto;
import com.workjo.pointapp.point.infrastructure.IPointEarnRepository;
import com.workjo.pointapp.point.infrastructure.IPointRepository;
import com.workjo.pointapp.user.infrastructure.UserRepository;
import jakarta.persistence.Convert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointServiceImple implements IPointService {

    private final IPointRepository pointRepository;
    private final IPointEarnRepository pointEarnRepository;
    private final UserRepository userRepository;
    private final IPointPolicy pointPolicy;

    @Override
    public boolean earnPoint(PointEarnDto earnDto) {
        // find bill record by  int storeId, int receiptId, UUID userUuid,
        // check if earned point by the bill, get paidPrice from it

        int pointAmount = pointPolicy.getPoint(earnDto.getPaidPrice());
        PointCreateDto createDto = new PointCreateDto(
                earnDto.getUserUuid(),
                pointAmount,
                PointType.BILL,
                "" /* change later*/
        );
        Point createdPoint = this.createPoint(createDto);

        PointEarn pointEarn = PointEarn.builder().
                receiptId(earnDto.getReceiptId()).
                paidPrice(earnDto.getPaidPrice()).
                storeId(earnDto.getStoreId()).
                pointId(createdPoint.getId()).
                partnerId(earnDto.getPartnerId()).
                receiptDisplayable(true /* find from bill db*/).
                build();

        return pointEarnRepository.savePointEarn(pointEarn);
    }


    @Convert(converter = PointTypeConverter.class)
    private Point createPoint(PointCreateDto createDto) {

        return null;
    }
    //teacher's
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
//        IPointRepository.save(Point.builder()
//                .point(pointAddDto.getPoint())
//                .totalPoint(pointAddDto.getPoint())
//                .pointType(pointType)
//                .user(foundUser)
//                .used(pointAddDto.getUsed())

//                .build());

    //    }

    @Override
    public List<PointGetDto> getPointHistoryOfUser(UUID userUuid, PointHistoryReqDto requestDto) {
        List<Point> pointList = pointRepository.findByUserUuidAndRegDateBetween(userUuid, requestDto.getHistoryStartDate(), requestDto.getHistoryEndDate());
        ModelMapper modelMapper = new ModelMapper();
        List<PointGetDto> pointGetDtoList = pointList.stream().map(point -> {
                    PointGetDto pointGetDto = new PointGetDto();
                    modelMapper.map(point, pointGetDto);
                    return pointGetDto;
                }
        ).collect(Collectors.toList());
//        log.info("pointList is : {}", pointList);
        return pointGetDtoList;
    }
}

