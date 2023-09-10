package com.workjo.pointapp.event.apply.infrastructure;

import com.workjo.pointapp.event.apply.domain.EventApply;
import com.workjo.pointapp.event.apply.domain.EventApplyStatus;
import com.workjo.pointapp.event.apply.vo.response.EventApplyRes;
import com.workjo.pointapp.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IEventApplyRepository extends JpaRepository<EventApply, Long> {

    List<EventApply> findByUserUuid(UUID userUuid);

    List<EventApply> findByUserUuidAndStatus(UUID userUuid, EventApplyStatus status);



}
