package com.workjo.pointapp.alarm.presentation;


import com.workjo.pointapp.alarm.application.AlarmService;
import com.workjo.pointapp.alarm.dto.AlarmGetDto;
import com.workjo.pointapp.alarm.vo.AlarmGetOut;
import com.workjo.pointapp.auth.AuthUtils;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.ModelMapperBean;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Alarm Controller", description = "유저 기능")
@RestController
@RequestMapping("/api/v1/alarm")
@Slf4j
@RequiredArgsConstructor
public class AlarmController {

	private final ModelMapperBean modelMapperBean;
	private final AlarmService alarmService;


	@GetMapping("")
	public ApiResponse<List<AlarmGetOut>> getUserAlarmList(Authentication authentication) {
		List<AlarmGetDto> alarmGetDtoList = alarmService.findAlarmByUserId(AuthUtils.getCurrentUserUUID(authentication));
		return ApiResponse.ofSuccess(alarmGetDtoList.stream().map(o -> modelMapperBean.privateStrictModelMapper().map(o, AlarmGetOut.class)).toList());
	}


	@PatchMapping("/{alarmId}")
	public ApiResponse<Void> modifyOneAlarmIsCheck(@PathVariable Long alarmId, Authentication authentication) {
		alarmService.modifyAlarmIsCheckByUserUUID(AuthUtils.getCurrentUserUUID(authentication), alarmId);
		return ApiResponse.ofSuccess(null);
	}


	@PatchMapping("/read-all")
	public ApiResponse<Void> modifyAllAlarmISCheck(Authentication authentication) {
		alarmService.modifyAllAlarmIsCheckByUserId(AuthUtils.getCurrentUserDto(authentication).getId());
		return ApiResponse.ofSuccess(null);
	}

}
