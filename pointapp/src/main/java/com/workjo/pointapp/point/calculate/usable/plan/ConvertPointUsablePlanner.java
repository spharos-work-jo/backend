package com.workjo.pointapp.point.calculate.usable.plan;

import com.workjo.pointapp.point.common.domain.observable.INotUsablePointObservable;
import com.workjo.pointapp.point.common.dto.PointEntityDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class ConvertPointUsablePlanner implements INotUsablePointObservable {

    private final IConvertPointUsablePlanRepository usableConvertPlanRepository;


    @Override
    public void observeUnusablePointSaved(PointEntityDto savedPointDto) {

        ConvertPointUsablePlan usableConvertPlan = ConvertPointUsablePlan.builder()
                .pointId(savedPointDto.getId())
                .pointAmount(savedPointDto.getPoint())
                .userUuid(savedPointDto.getUserUuid())
                .build();

        usableConvertPlanRepository.save(usableConvertPlan);
    }
}
