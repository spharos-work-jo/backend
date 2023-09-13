package com.workjo.pointapp.point.calculate.expire.calculator;

import com.workjo.pointapp.point.calculate.expire.planner.PointExpirePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPointExpirePlanRepository extends JpaRepository<PointExpirePlan, Long> {
}
