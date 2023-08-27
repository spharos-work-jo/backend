package com.workjo.pointapp.point.application;

import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
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

import java.util.ArrayList;
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
    public void earnPoint(PointEarnDto earnDto) {//todo 적립 요청된 포인트 정산 처리 schedule 구현
//        todo bill이 포인트 적립에 이미 사용되었는지 확인
        // Bill bill = billRepository.findBillBy(storeId, receiptId, UUID userUuid)
        // if bill.getIsUsedToPoint
        // dto.isSaved = false;
        // return;


        // 포인트 엔티티 생성 및 포인트 테이블 저장
//        int pointAmount =  pointPolicy.getPoint(bill.getPaidPrice);
        int point = pointPolicy.getPoint(earnDto.getPaidPrice());

        PointCreateDto createDto = new PointCreateDto(
                earnDto.getUserUuid(),
                point,
                PointType.BILL,
                PointType.BILL.getCode()/* change later*/,
                0
        );
        Point createdPoint = this.createNotUsablePoint(createDto);

        earnDto.setPointId(createdPoint.getId());
        earnDto.setReceiptDisplayable(true);/* todo find from bill db*/
        // 포인트 적립 테이블 저장
        PointEarn pointEarn = new PointEarn();
        modelMapper.map(earnDto, pointEarn);

        PointEarn savedEntity = pointEarnRepository.save(pointEarn);
        earnDto.setIsSucceeded(savedEntity != null);
    }


    @Override
    public List<PointDto> getPointHistoryOfUser(PointHistoryDto dto) {
        List<Point> pointList = pointRepository.
                findByUserUuidAndPointTypeAndRegDateBetweenOrderByRegDateDesc
                        (dto.getUserUuid(), dto.getPointType(), dto.getHistoryStartDate(), dto.getHistoryEndDate());
        if (pointList == null) {
            return new ArrayList<PointDto>();
        }


        List<PointDto> pointDtoList = pointList.stream().map(
                point -> {
                    PointDto pointDto = new PointDto();
                    modelMapper.map(point, pointDto);
                    return pointDto;
                }
        ).collect(Collectors.toList());

        return pointDtoList;
    }


    //todo refactoring : dao code below, if wanna refactor, search repository used code and separate them all
    private Point createUsablePoint(PointCreateDto createDto) {
        int totalBeforeCreate = getTotalPoint(createDto.getUserUuid());
        int totalAfterCreate = createDto.getPoint() + totalBeforeCreate;
        createDto.setTotalPoint(totalAfterCreate);

        return this.savePoint(createDto);
    }

    private Point createNotUsablePoint(PointCreateDto createDto) {
        int totalPoint = getTotalPoint(createDto.getUserUuid());
        createDto.setTotalPoint(totalPoint);


        return this.savePoint(createDto);
    }

    private Point savePoint(PointCreateDto dto) {
        Point point = new Point();
        modelMapper.map(dto, point);

        Point savePoint = pointRepository.save(point);
        if (savePoint == null) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR/*todo 에러코드쪽 디버깅 되면 POINTSAVEFAILED 에러코드 정의해서 쓰기*/);
        }
        return savePoint;
    }


    private int getTotalPoint(UUID userUuid) { //todo consider server cache
        Point point = pointRepository.findFirstByUserUuidOrderByRegDateDesc(userUuid);
        if (point == null) {
            return 0;
        }

        return point.getTotalPoint();
    }
}