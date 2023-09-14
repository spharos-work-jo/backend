package com.workjo.pointapp.point.calculate.expire.calculated;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPointExpiredRepository extends  JpaRepository<PointExpired, Long> {
}
