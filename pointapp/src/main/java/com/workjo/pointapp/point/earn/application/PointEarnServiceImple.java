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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointEarnServiceImple implements IPointEarnService {
    private final IPointPolicy pointPolicy;
    private final ModelMapper modelMapper;
    private final IPointEarnRepository pointEarnRepository;


    @Override
    @Transactional
    public void earnPoint(EarnPointDto earnDto, IPointService pointService) {
        //todo 적립 요청된 포인트 정산 처리 schedule 구현

        PointEntityDto createdPoint =
                pointService.saveTotalNotRenewedPoint(
                        new CreatePointDto(
                                earnDto.getUserUuid(),
                                pointPolicy.getPoint(earnDto.getPaidPrice()),
                                PointType.EARN,
                                PointType.EARN.getCode()/* change later*/
                        )
                );
        earnDto.setPointId(createdPoint.getId());

        // 포인트 적립 테이블 저장
        PointEarn pointEarn =
                modelMapper.map(earnDto, PointEarn.class);
        PointEarn savedEntity =
                pointEarnRepository.save(pointEarn);
        earnDto.setIsSucceeded(savedEntity != null);
    }
}
