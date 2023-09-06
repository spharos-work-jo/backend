package com.workjo.pointapp.event.dto;

import com.workjo.pointapp.event.domain.EventType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GetEventsDto {
    private LocalDateTime minEndDate;
    private LocalDateTime maxEndDate;
    private List<EventType> eventTypesToSearch;

    @Builder
    public GetEventsDto(LocalDateTime minEndDate, LocalDateTime maxEndDate, List<EventType> eventTypesToSearch) {
        this.minEndDate = minEndDate;
        this.maxEndDate = maxEndDate;
        this.eventTypesToSearch = eventTypesToSearch;
    }
}
