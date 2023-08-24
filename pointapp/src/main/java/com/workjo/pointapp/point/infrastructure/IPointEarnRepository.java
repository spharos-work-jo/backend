package com.workjo.pointapp.point.infrastructure;

import com.workjo.pointapp.point.domain.Point;
import com.workjo.pointapp.point.domain.PointEarn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPointEarnRepository extends JpaRepository<PointEarn, Long> {
}
