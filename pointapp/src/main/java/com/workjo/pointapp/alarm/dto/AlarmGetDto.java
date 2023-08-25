package com.workjo.pointapp.alarm.dto;


import com.workjo.pointapp.alarm.domain.AlarmType;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlarmGetDto {

	private Long id;
	private String content;
	private Boolean isCheck;
	private AlarmType type;

}
