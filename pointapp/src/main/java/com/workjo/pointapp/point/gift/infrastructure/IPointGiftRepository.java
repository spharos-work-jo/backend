package com.workjo.pointapp.point.gift.infrastructure;

import com.workjo.pointapp.point.gift.domain.PointGift;
import com.workjo.pointapp.point.gift.domain.PointGiftStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IPointGiftRepository extends JpaRepository<PointGift, Long> {
    List<PointGift> findByToUserUuid(UUID toUserUuid);

    Optional<PointGift> findById(Long id);

    List<PointGift> findByToUserUuidAndGiftStatus(UUID toUserUuid, PointGiftStatus status);
}
