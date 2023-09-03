package com.workjo.pointapp.point.earn;

import com.workjo.pointapp.point.earn.domain.PointEarn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPointEarnRepository extends JpaRepository<PointEarn, Long> {
}
