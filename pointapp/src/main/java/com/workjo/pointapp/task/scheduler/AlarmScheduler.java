package com.workjo.pointapp.task.scheduler;


import com.workjo.pointapp.alarm.application.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Log4j2
@RequiredArgsConstructor
public class AlarmScheduler {

	private final AlarmService alarmService;


	/**
	 * 매일 AM 4시에 7일 이전의 알람을 삭제한다.
	 */
	@Scheduled(cron = "0 0 4 * * *")    // 매일 AM 4시
	public void deleteOutOfDateAlarm() {
		log.info("[timer scheduler] start");
		try {
			// 7일 이전의 알람을 삭제한다.
			List<Long> alarmIdList = alarmService.findAlarmIdOutOfDate();
			alarmService.deleteOutOfDateAlarm(alarmIdList);
			log.info("[timer scheduler] end");
		} catch (Exception e) {
			log.error(e.getMessage());
			log.info("[timer scheduler] error");

		}
	}

}
