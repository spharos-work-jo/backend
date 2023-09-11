package com.workjo.pointapp.event.apply.application;

import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.event.apply.domain.EventApplyStatus;
import com.workjo.pointapp.event.apply.infrastructure.IEventApplyRepository;
import com.workjo.pointapp.event.apply.domain.EventApply;
import com.workjo.pointapp.event.apply.dto.EventApplyDto;
import com.workjo.pointapp.event.apply.dto.FindAppliedEventsByStatusDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventApplyServiceImple implements IEventApplyService {

    private final IEventApplyRepository eventApplyRepository;
    private final ModelMapper modelMapper;

    @Override
    public void applyEvent(EventApplyDto dto) {
        EventApply eventApply =
                EventApply.builder()
                        .eventId(dto.getEventId())
                        .userUuid(dto.getUserUuid())
                        .status(EventApplyStatus.NOT_DRAWN)
                        .build();

        try {
            eventApplyRepository.save(eventApply);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.ENTITY_SAVE_FAILED);
        }

    }

    @Override
    public List<EventApplyDto> findByUserUuid(UUID userUuid) {

        List<EventApply> eventApplies =
                eventApplyRepository.findByUserUuid(userUuid);

        List<EventApplyDto> eventApplyDtos =
                eventApplies.stream()
                        .map(eventApply ->
                                modelMapper.map(eventApply, EventApplyDto.class))
                        .collect(Collectors.toList());

        return eventApplyDtos;
    }

    @Override
    public List<EventApplyDto> findByUserUuidAndStatus(FindAppliedEventsByStatusDto findDto) {
        List<EventApply> eventApplies =
                eventApplyRepository.findByUserUuidAndStatus
                        (findDto.getUserUuid(), findDto.getStatus());

        List<EventApplyDto> eventApplyDtos =
                eventApplies.stream()
                        .map(eventApply -> modelMapper.map(eventApply, EventApplyDto.class))
                        .collect(Collectors.toList());

        return eventApplyDtos;
    }


    @Override
    public EventApplyDto findById(Long id) {

        EventApply eventApply =
                eventApplyRepository.findById(id)
                        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));

        return modelMapper.map(eventApply, EventApplyDto.class);
    }
}
