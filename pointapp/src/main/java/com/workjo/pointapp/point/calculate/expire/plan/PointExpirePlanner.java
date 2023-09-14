package com.workjo.pointapp.point.calculate.expire.plan;

import com.workjo.pointapp.point.common.dto.PointEntityDto;
import com.workjo.pointapp.point.common.domain.observable.IUsablePointObservable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PointExpirePlanner implements IUsablePointObservable {

    private final IPointExpirePlanRepository expirePlanRepository;


    @Override
    @Async
    public void observePointUsed(PointEntityDto usedPoint) {

        //todo pointExpirePlanRepository.findFirstByUserUuid 해서 사용 포인트 만큼 만료 포인트 없애나가기
    }


    @Override
    public void observeUsablePointIncreased(PointEntityDto usablePoint) {
        PointExpirePlan plan = PointExpirePlan.builder()
                .pointAmount(usablePoint.getPoint())
                .pointId(usablePoint.getId())
                .build();

        expirePlanRepository.save(plan);
    }
}
