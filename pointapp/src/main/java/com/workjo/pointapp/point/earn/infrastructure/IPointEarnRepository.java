package com.workjo.pointapp.point.earn.infrastructure;

import com.workjo.pointapp.point.earn.domain.PointEarn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IPointEarnRepository extends JpaRepository<PointEarn, Long> {
    @Transactional(readOnly = true)
    List<PointEarn> findByUsableStartDate(LocalDateTime usableStartDate);
}
