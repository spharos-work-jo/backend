package com.workjo.pointapp.point.calculate.expire;

import com.workjo.pointapp.point.calculate.expire.calculated.IPointExpiredRepository;
import com.workjo.pointapp.point.calculate.expire.calculated.PointExpired;
import com.workjo.pointapp.point.calculate.expire.plan.IPointExpirePlanRepository;
import com.workjo.pointapp.point.calculate.expire.plan.PointExpirePlan;
import com.workjo.pointapp.point.common.application.IPointService;
import com.workjo.pointapp.point.common.infrastructure.IPointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
//만료 포인트 정산
public class PointExpireCalculator {

    private final IPointExpirePlanRepository pointExpirePlanRepository;

    private final IPointExpiredRepository pointExpiredRepository;
    private final IPointRepository pointRepository;
    private final IPointService pointService;


    @Transactional// 매월 1일에 schedule
    public void calculateExpirePlan() {
        // todo point테이블에 regDate, uuid로 인덱싱 걸기 -> total포인트 갱신해줄때 로드 많이 걸릴 수 밖에 없음
        //todo PointExpireDatePlan, PointExpired 테이블에도 expireDate로 인덱스 걸기

        List<PointExpirePlan> expirePlans
                = pointExpirePlanRepository.findByExpireDate(LocalDate.now());
        List<PointExpired> pointExpiredList = new ArrayList<>();
        HashMap<UUID, Integer> totalPointOfUser = new HashMap<>();

        expirePlans.stream().forEach(plan -> {

            UUID userUuid = plan.getUserUuid();

            if (!totalPointOfUser.containsKey(userUuid)) {
                int totalPoint = pointService.getTotalPoint(userUuid);
                totalPointOfUser.put(userUuid, totalPoint);
            }

            Integer totalPoint = totalPointOfUser.get(userUuid);
//            plan.get

        });


    }

    private void expirePointAsPlanned(PointExpirePlan plan) {

    }

}
