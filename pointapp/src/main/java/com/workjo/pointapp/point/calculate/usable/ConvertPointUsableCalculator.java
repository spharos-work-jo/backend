package com.workjo.pointapp.point.calculate.usable;


import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.point.calculate.usable.calculated.IPointUsableConvertedRepository;
import com.workjo.pointapp.point.calculate.usable.calculated.PointUsableConverted;
import com.workjo.pointapp.point.calculate.usable.plan.IConvertPointUsablePlanRepository;
import com.workjo.pointapp.point.calculate.usable.plan.ConvertPointUsablePlan;
import com.workjo.pointapp.point.common.application.IPointService;
import com.workjo.pointapp.point.common.dto.PointEntityDto;
import com.workjo.pointapp.point.common.domain.Point;
import com.workjo.pointapp.point.common.infrastructure.IPointRepository;
import com.workjo.pointapp.point.common.domain.observable.IUsablePointObservable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SuppressWarnings("Convert2MethodRef")

@Service
@RequiredArgsConstructor
@Slf4j
//
public class ConvertPointUsableCalculator {

    private final IConvertPointUsablePlanRepository usableConvertPlanRepository;
    private final IPointUsableConvertedRepository usableConvertedRepository;
    private final IPointRepository pointRepository;

    private final List<IUsablePointObservable> usablePointObservers;
    private final IPointService pointService;

    // @Scheduled(cron = "1 0 0 * * *") 
    @Transactional
    public void calculateUsableConvertPlan() {

        List<ConvertPointUsablePlan> allPlansOrderByUserUuid =
                usableConvertPlanRepository.findAllByOrderByUserUuid();

        if (allPlansOrderByUserUuid.isEmpty()) return;


        // convert point to usable as plan, renew total point
        int totalPointToConvert = 0;
        UUID nowUserUuid = allPlansOrderByUserUuid.get(0).getUserUuid();
        Long lastPointId = -1L;
        allPlansOrderByUserUuid.add(null);

        for (ConvertPointUsablePlan plan : allPlansOrderByUserUuid
        ) {
            // 한 유저의 convertPlan 모두 취합해 total 갱신, 다음유저 정보 취합 위해 초기화
            if (plan == null || !nowUserUuid.equals(plan.getUserUuid())) {
                if (lastPointId != -1L) {

                    Point lastPoint = pointRepository.findById(lastPointId)
                            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));

                    int renewedTotalPoint
                            = lastPoint.getPoint() + totalPointToConvert;

                    lastPoint.setTotalPoint(renewedTotalPoint);
                    pointRepository.save(lastPoint);


                    if (plan != null) {
                        lastPointId = -1L;
                        totalPointToConvert = 0;
                        nowUserUuid = plan.getUserUuid();
                    }
                }
            }

            totalPointToConvert += plan.getPointAmount();

            if (!usablePointObservers.isEmpty()) {
                PointEntityDto usableConverted
                        = pointService.findPointById(plan.getPointId());

                usablePointObservers.forEach(observer ->
                        observer.observeUsablePointIncreased(usableConverted));
            }

            lastPointId = Math.max(plan.getPointId(), lastPointId);
        }


        List<PointUsableConverted> converteds
                = allPlansOrderByUserUuid.stream()
                .map(plan -> PointUsableConverted.of(plan))
                .collect(Collectors.toList());

        usableConvertedRepository.saveAll(converteds);
        usableConvertPlanRepository.deleteAll();
    }

}
