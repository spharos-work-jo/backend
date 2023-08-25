package com.workjo.pointapp.alarm.application;


import com.workjo.pointapp.alarm.domain.Alarm;
import com.workjo.pointapp.alarm.domain.AlarmType;
import com.workjo.pointapp.alarm.dto.AlarmCreateDto;
import com.workjo.pointapp.alarm.dto.AlarmGetDto;
import com.workjo.pointapp.alarm.infrastructure.AlarmRepository;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.user.application.UserService;
import com.workjo.pointapp.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AlarmServiceImple implements AlarmService {

	private final ModelMapperBean modelMapperBean;

	private final AlarmRepository alarmRepository;
	private final UserService userService;


	@Override
	public void createAlarm(AlarmCreateDto alarmCreateDto) {
		User receiveUser = userService.getUserByUUID(alarmCreateDto.getUserUuid());
		alarmRepository.save(Alarm.builder()
			.content(alarmCreateDto.getContent())
			.user(receiveUser)
			.type(AlarmType.COUPON)
			.isCheck(false)
			.build());
	}


	@Override
	public void readAlarm(Long userId, Long alarmId) {
		alarmRepository.updateAlarmCheckByUserIdAndAlarmId(userId, alarmId);
	}


	@Override
	public void readAllAlarm(Long userId) {
		alarmRepository.updateAlarmCheckByUserId(userId);
	}


	@Override
	public void deleteOutOfDateAlarm() {
		LocalDateTime dateTime = LocalDateTime.now().minusDays(7L);
		alarmRepository.deleteAllByRegDateBefore(dateTime);
	}


	@Override
	public List<AlarmGetDto> findAlarmByUserId(Long id) {
		List<Alarm> alarmList = alarmRepository.findAlarmByUserId(id);
		return alarmList.stream()
			.map(o -> modelMapperBean.privateStrictModelMapper().map(o, AlarmGetDto.class))
			.toList();
	}

}
