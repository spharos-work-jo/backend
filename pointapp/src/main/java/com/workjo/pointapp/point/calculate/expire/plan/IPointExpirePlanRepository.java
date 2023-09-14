package com.workjo.pointapp.point.calculate.expire.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPointExpirePlanRepository extends JpaRepository<PointExpirePlan, Long> {
    List<PointExpirePlan> findByExpireDate(LocalDate expireDate);
}
