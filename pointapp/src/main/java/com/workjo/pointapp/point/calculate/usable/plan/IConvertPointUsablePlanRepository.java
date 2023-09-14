package com.workjo.pointapp.point.calculate.usable.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IConvertPointUsablePlanRepository extends JpaRepository<ConvertPointUsablePlan, Long> {
    List<ConvertPointUsablePlan> findAllByOrderByUserUuid();
}
