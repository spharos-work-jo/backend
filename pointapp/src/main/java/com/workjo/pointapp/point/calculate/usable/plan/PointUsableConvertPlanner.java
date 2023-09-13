package com.workjo.pointapp.point.calculate.usable.plan;

import com.workjo.pointapp.point.observable.INotUsablePointObservable;
import com.workjo.pointapp.point.common.dto.PointEntityDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class PointUsableConvertPlanner implements INotUsablePointObservable {

    private final IPointUsableConvertPlanRepository usableConvertPlanRepository;


    @Override
    public void observeUnusablePointSaved(PointEntityDto savedPointDto) {

        PointUsableConvertPlan usableConvertPlan = PointUsableConvertPlan.builder()
                .pointId(savedPointDto.getId())
                .pointAmount(savedPointDto.getPoint())
                .userUuid(savedPointDto.getUserUuid())
                .build();

        usableConvertPlanRepository.save(usableConvertPlan);
    }
}
