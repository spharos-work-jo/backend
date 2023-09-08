package com.workjo.pointapp.alarm.application;


import com.workjo.pointapp.alarm.domain.Alarm;
import com.workjo.pointapp.alarm.domain.AlarmType;
import com.workjo.pointapp.alarm.dto.AlarmCreateDto;
import com.workjo.pointapp.alarm.dto.AlarmGetDto;
import com.workjo.pointapp.alarm.infrastructure.AlarmCustomRepository;
import com.workjo.pointapp.alarm.infrastructure.AlarmRepository;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.user.application.UserService;
import com.workjo.pointapp.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AlarmServiceImple implements AlarmService {

	private final ModelMapperBean modelMapperBean;

	private final AlarmRepository alarmRepository;
	private final AlarmCustomRepository alarmCustomRepository;
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
	@Transactional
	public void modifyAlarmIsCheckByUserUUID(UUID uuid, Long alarmId) {
		Alarm alarm = alarmRepository.findById(alarmId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));
		alarm.updateIsCheckTrue();
	}


	@Override
	@Transactional
	public void modifyAllAlarmIsCheckByUserId(Long userId) {
		alarmCustomRepository.updateAlarmIsCheckByUserId(userId);
	}


	@Override
	public void deleteOutOfDateAlarm() {
		LocalDateTime dateTime = LocalDateTime.now().minusDays(7L);
		alarmRepository.deleteAllByRegDateBefore(dateTime);
	}


	@Override
	public List<AlarmGetDto> findAlarmByUserId(UUID uuid) {
		User user = userService.getUserByUUID(uuid);
		List<Alarm> alarmList = alarmRepository.findByUser(user);
		return alarmList.stream()
			.map(o -> modelMapperBean.privateStrictModelMapper().map(o, AlarmGetDto.class))
			.toList();
	}

}
