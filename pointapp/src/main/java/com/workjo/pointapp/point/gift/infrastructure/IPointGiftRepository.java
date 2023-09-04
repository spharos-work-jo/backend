package com.workjo.pointapp.point.gift.infrastructure;

import com.workjo.pointapp.point.gift.domain.PointGift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IPointGiftRepository extends JpaRepository<PointGift, Long> {
    List<PointGift> findByToUserUuid(UUID toUserUuid);//todo 리스트 원소 optional로 수정

    Optional<PointGift> findById(Long id);

    List<PointGift> findByToUserUuidAndGiftStatus(UUID toUserUuid, Character pointGiftStatus);
}
