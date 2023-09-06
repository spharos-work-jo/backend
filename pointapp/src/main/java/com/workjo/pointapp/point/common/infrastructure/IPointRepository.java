package com.workjo.pointapp.point.common.infrastructure;

import com.workjo.pointapp.point.common.domain.Point;
import com.workjo.pointapp.point.common.domain.PointType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IPointRepository extends JpaRepository<Point, Long> {
    List<Point> findByUserUuidAndPointTypeInAndRegDateBetweenOrderByRegDateDesc
            (UUID userUuid, List<PointType> pointTypes, LocalDateTime historyEndDate, LocalDateTime historyStartDate);

    Optional<Point> findFirstByUserUuidOrderByRegDateDesc(UUID userUuid);


    //findAll() for admin
}
