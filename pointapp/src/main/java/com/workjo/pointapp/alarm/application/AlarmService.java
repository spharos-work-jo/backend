package com.workjo.pointapp.alarm.application;


import com.workjo.pointapp.alarm.dto.AlarmCreateDto;
import com.workjo.pointapp.alarm.dto.AlarmGetDto;

import java.util.List;


public interface AlarmService {

	void createAlarm(AlarmCreateDto alarmCreateDto);
	void readAlarm(Long userId, Long alarmId);
	void readAllAlarm(Long userId);

	void deleteOutOfDateAlarm();

	List<AlarmGetDto> findAlarmByUserId(Long id);

}
