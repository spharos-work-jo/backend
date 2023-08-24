package com.workjo.pointapp.point.application;

import com.workjo.pointapp.point.application.pointpolicy.IPointPolicy;
import com.workjo.pointapp.point.domain.Point;
import com.workjo.pointapp.point.domain.PointEarn;
import com.workjo.pointapp.point.domain.PointType;
import com.workjo.pointapp.point.dto.PointCreateDto;
import com.workjo.pointapp.point.dto.PointEarnDto;
import com.workjo.pointapp.point.dto.PointDto;
import com.workjo.pointapp.point.dto.PointHistoryDto;
import com.workjo.pointapp.point.infrastructure.IPointEarnRepository;
import com.workjo.pointapp.point.infrastructure.IPointRepository;
import com.workjo.pointapp.user.infrastructure.UserRepository;
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
    private final ModelMapper modelMapper;

    @Override
    public boolean earnPoint(PointEarnDto earnDto) {//todo 적립 요청된 포인트 정산 처리 schedule 구현
//        todo bill이 포인트 적립에 이미 사용되었는지 확인
        // Bill bill = billRepository.findBillBy(storeId, receiptId, UUID userUuid)
        // if bill.getIsUsedToPoint
        // return false;


        // 포인트 엔티티 생성 및 포인트 테이블 저장
//        int pointAmount =  pointPolicy.getPoint(bill.getPaidPrice);
        int pointAmount = pointPolicy.getPoint(earnDto.getPaidPrice());
        PointCreateDto createDto = new PointCreateDto(
                earnDto.getUserUuid(),
                pointAmount,
                PointType.EARN,
                PointType.EARN.getCode()/* change later*/
        );
        Point createdPoint = this.createPoint(createDto);

        // 포인트 적립 테이블 저장
        PointEarn pointEarn = PointEarn.builder().
                receiptId(earnDto.getReceiptId()).
                paidPrice(earnDto.getPaidPrice()).
                storeId(earnDto.getStoreId()).
                pointId(createdPoint.getId()).
                partnerId(earnDto.getPartnerId()).
                receiptDisplayable(true /* find from bill db*/).
                build();
        pointEarnRepository.save(pointEarn);

        return true;
    }




    @Override
    public List<PointDto> getPointHistoryOfUser(PointHistoryDto dto) {
        List<Point> pointList = pointRepository.
                findByUserUuidAndPointTypeAndRegDateBetweenOrderByRegDate
                        (dto.getUserUuid(), dto.getPointType(), dto.getHistoryStartDate(), dto.getHistoryEndDate());

        List<PointDto> pointDtoList = pointList.stream().map(
                point -> {
                    PointDto pointDto = new PointDto();
                    modelMapper.map(point, pointDto);
                    return pointDto;
                }
        ).collect(Collectors.toList());
//        log.info("pointList is : {}", pointList);

        return pointDtoList;
    }


    private Point createPoint(PointCreateDto createDto) {
        int lastTotalPoint = findLastPointOfUser(createDto.getUserUuid()).getTotalPoint();
        int nowTotalPoint = createDto.getPoint() + lastTotalPoint;

        Point point = Point.builder().
                totalPoint(nowTotalPoint).
                build();
        modelMapper.map(createDto, point);

        return pointRepository.save(point);
    }

    private Point findLastPointOfUser(UUID userUuid) { //consider server cache
        return pointRepository.findFirstByUserUuidOrderByRegDateDesc(userUuid);
    }
}