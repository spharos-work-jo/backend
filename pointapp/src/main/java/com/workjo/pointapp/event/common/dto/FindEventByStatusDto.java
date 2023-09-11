package com.workjo.pointapp.event.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindEventByStatusDto {
    private String sortedBy;
    private String eventStatus;

}
