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
    }


    @Override
    public void observeUsablePointIncreased(PointEntityDto usablePoint) {
        PointExpirePlan plan = new PointExpirePlan(
                usablePoint.getId(),
                usablePoint.getPoint(),
                usablePoint.getUserUuid()
        );

        expirePlanRepository.save(plan);
    }
}
