package com.workjo.pointapp.point.earn.application;

import com.workjo.pointapp.point.common.application.IPointService;
import com.workjo.pointapp.point.common.domain.PointType;
import com.workjo.pointapp.point.common.dto.CreatePointDto;
import com.workjo.pointapp.point.common.application.pointpolicy.IPointPolicy;
import com.workjo.pointapp.point.common.dto.PointEntityDto;
import com.workjo.pointapp.point.earn.infrastructure.IPointEarnRepository;
import com.workjo.pointapp.point.earn.domain.PointEarn;
import com.workjo.pointapp.point.earn.dto.EarnPointDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointEarnServiceImple implements IPointEarnService {
    private final IPointPolicy pointPolicy;
    private final ModelMapper modelMapper;
    private final IPointEarnRepository pointEarnRepository;


    @Override
    public void earnPoint(EarnPointDto earnDto, IPointService pointService) {//todo 적립 요청된 포인트 정산 처리 schedule 구현
//        todo bill이 포인트 적립에 이미 사용되었는지 확인
        // Bill bill = billRepository.findBillBy(storeId, receiptId, UUID userUuid)
        // if bill.getIsUsedToPoint
        // dto.isSaved = false;
        // return;


        // 포인트 엔티티 생성 및 포인트 테이블 저장
//        int pointAmount =  pointPolicy.getPoint(bill.getPaidPrice);
        int point = pointPolicy.getPoint(earnDto.getPaidPrice());

        CreatePointDto createDto = new CreatePointDto(
                earnDto.getUserUuid(),
                point,
                PointType.EARN,
                PointType.EARN.getCode()/* change later*/
        );
        PointEntityDto createdPoint = pointService.saveTotalNotRenewedPoint(createDto);

        earnDto.setPointId(createdPoint.getId());
        earnDto.setReceiptDisplayable(true);/* todo find from bill db*/
        // 포인트 적립 테이블 저장
        PointEarn pointEarn = new PointEarn();
        modelMapper.map(earnDto, pointEarn);

        PointEarn savedEntity = pointEarnRepository.save(pointEarn);//todo pointEarn dao
        earnDto.setIsSucceeded(savedEntity != null);
    }
}
