package com.workjo.pointapp.point.infrastructure;

import com.workjo.pointapp.point.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PointRepository extends JpaRepository<Point, Long>{
    List<Point> findByUuid(UUID uuid);
}
