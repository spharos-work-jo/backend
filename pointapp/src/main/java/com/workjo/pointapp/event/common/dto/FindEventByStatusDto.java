package com.workjo.pointapp.event.common.dto;

import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindEventByStatusDto {
    private LocalDateTime minEndDate;
    private LocalDateTime maxEndDate;
    private LocalDateTime minDrawDate;

    public FindEventByStatusDto(String eventStatus) {
        switch (eventStatus) {
            case "ongoing" -> {
                minEndDate = LocalDateTime.now();
                maxEndDate = LocalDateTime.MAX;
            }
            case "end" -> {
                minEndDate = LocalDateTime.MIN;
                maxEndDate = LocalDateTime.now();
            }
            case "drawn" -> minDrawDate = LocalDateTime.now();
            default -> throw new CustomException(ErrorCode.BAD_REQUEST);
        }
    }
}
