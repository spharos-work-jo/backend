package com.workjo.pointapp.alarm.dto;


import com.workjo.pointapp.alarm.domain.AlarmType;
import lombok.*;

import java.util.UUID;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlarmCreateDto {

	private AlarmType type;
	private UUID userUuid;
	private String content;

}
