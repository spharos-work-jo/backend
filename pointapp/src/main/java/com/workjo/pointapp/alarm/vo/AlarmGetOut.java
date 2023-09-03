package com.workjo.pointapp.alarm.vo;


import com.workjo.pointapp.alarm.domain.AlarmType;
import lombok.Getter;


@Getter
public class AlarmGetOut {

	private Long id;
	private String content;
	private Boolean isCheck;
	private AlarmType type;

}
