package com.workjo.pointapp.point.calculate.usable.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPointUsableConvertPlanRepository extends JpaRepository<PointUsableConvertPlan, Long> {
    List<PointUsableConvertPlan> findAllByOrderByUserUuid();
}
