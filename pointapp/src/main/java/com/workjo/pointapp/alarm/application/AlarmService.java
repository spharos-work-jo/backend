package com.workjo.pointapp.alarm.application;


import com.workjo.pointapp.alarm.dto.AlarmCreateDto;
import com.workjo.pointapp.alarm.dto.AlarmGetDto;

import java.util.List;
import java.util.UUID;


public interface AlarmService {

	void createAlarm(AlarmCreateDto alarmCreateDto);
	void modifyAlarmIsCheckByUserUUID(UUID uuid, Long alarmId);
	void modifyAllAlarmIsCheckByUserId(Long userId);

	void deleteOutOfDateAlarm();

	List<AlarmGetDto> findAlarmByUserId(UUID uuid);

}
