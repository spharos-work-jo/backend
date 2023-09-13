package com.workjo.pointapp.alarm.presentation;


import com.workjo.pointapp.alarm.application.AlarmService;
import com.workjo.pointapp.alarm.dto.AlarmGetDto;
import com.workjo.pointapp.alarm.vo.AlarmGetOut;
import com.workjo.pointapp.auth.AuthUtils;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.ModelMapperBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Alarm Controller", description = "알람 관련 API를 담당하는 컨트롤러 입니다.")
@RestController
@RequestMapping("/api/v1/alarm")
@Slf4j
@RequiredArgsConstructor
public class AlarmController {

	private final ModelMapperBean modelMapperBean;
	private final AlarmService alarmService;


	@Operation(summary = "유저 알람 리스트 조회", description = "유저의 전체 알람 리스트 조회")
	@GetMapping("")
	public ApiResponse<List<AlarmGetOut>> getUserAlarmList(Authentication authentication) {
		List<AlarmGetDto> alarmGetDtoList = alarmService.findAlarmByUserId(AuthUtils.getCurrentUserUUID(authentication));
		return ApiResponse.ofSuccess(alarmGetDtoList.stream().map(o -> modelMapperBean.privateStrictModelMapper().map(o, AlarmGetOut.class)).toList());
	}


	@Operation(summary = "유저 개별 알람 읽음처리", description = "알람 클릭시 읽음처리")
	@PatchMapping("/{alarmId}")
	public ApiResponse<Void> modifyOneAlarmIsCheck(@PathVariable Long alarmId, Authentication authentication) {
		alarmService.modifyAlarmIsCheckByUserUUID(AuthUtils.getCurrentUserUUID(authentication), alarmId);
		return ApiResponse.ofSuccess(null);
	}


	@Operation(summary = "유저 알람 모두 읽기", description = "유저 알람 모두 읽기")
	@PatchMapping("/read-all")
	public ApiResponse<Void> modifyAllAlarmISCheck(Authentication authentication) {
		alarmService.modifyAllAlarmIsCheckByUserId(AuthUtils.getCurrentUserDto(authentication).getId());
		return ApiResponse.ofSuccess(null);
	}

}
