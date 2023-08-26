package com.workjo.pointapp.alarm.vo;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.workjo.pointapp.alarm.domain.AlarmType;
import lombok.Getter;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
public class AlarmGetOut {

	private Long id;
	private String content;
	private Boolean isCheck;
	private AlarmType type;

}
