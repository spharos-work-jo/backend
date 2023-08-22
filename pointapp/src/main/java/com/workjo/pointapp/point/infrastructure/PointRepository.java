package com.workjo.pointapp.point.infrastructure;

import com.workjo.pointapp.point.domain.Point;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findByUserUuidAndRegDateBetween(UUID uuid, LocalDateTime historyStartDate, LocalDateTime historyEndDate);
}
