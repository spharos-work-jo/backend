package com.workjo.pointapp.event.apply.application;

import com.workjo.pointapp.event.apply.dto.EventApplyDto;
import com.workjo.pointapp.event.apply.dto.FindAppliedEventsByStatusDto;

import java.util.List;
import java.util.UUID;

public interface IEventApplyService {

    EventApplyDto findById(Long id);

    List<EventApplyDto> findByUserUuid(UUID userUuid);

    List<EventApplyDto> findByUserUuidAndStatus(FindAppliedEventsByStatusDto findDto);

    void applyEvent(EventApplyDto dto);
}
