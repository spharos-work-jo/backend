package com.workjo.pointapp.alarm.infrastructure;


import java.util.List;


public interface AlarmCustomRepository {

	void updateAlarmIsCheckByUserId(Long id);
	void deleteOutOfDateAlarm(List<Long> idList);

}
