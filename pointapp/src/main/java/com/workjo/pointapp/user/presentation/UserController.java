package com.workjo.pointapp.user.presentation;


import com.workjo.pointapp.auth.AuthUtils;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.user.application.UserService;
import com.workjo.pointapp.user.dto.UserFindDto;
import com.workjo.pointapp.user.dto.UserGetDto;
import com.workjo.pointapp.user.dto.UserPwDto;
import com.workjo.pointapp.user.vo.UserFindIn;
import com.workjo.pointapp.user.vo.UserIdCheckIn;
import com.workjo.pointapp.user.vo.UserUpdatePointPwIn;
import com.workjo.pointapp.user.vo.UserUpdatePwIn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Tag(name = "User Controller", description = "유저 기능")
@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

	private final ModelMapperBean modelMapperBean;

	private final UserService userService;


	@Operation(summary = "유저 탈퇴", description = "유저 탈퇴")
	@DeleteMapping("")
	public ApiResponse<Void> deleteUser(Authentication authentication) {
		UUID uuid = AuthUtils.getCurrentUserUUID(authentication);
		userService.softDeleteUserByUUID(uuid);
		return ApiResponse.ofSuccess(null);
	}


	@Operation(summary = "선물할 유저 UUID 찾기", description = "핸드폰 번호와 실명으로 다른 유저의 UUID 조회, 선물하기에서 사용")
	@PostMapping("/find-for-gift")
	public ApiResponse<String> getUserUUIDByPhoneAndName(@RequestBody UserFindIn userFindIn, Authentication authentication) {
		UserGetDto userDto = AuthUtils.getCurrentUserDto(authentication);
		if (userFindIn.getName().equals(userDto.getName()) && userFindIn.getPhone().equals(userDto.getPhone())) {
			throw new CustomException(ErrorCode.FIND_SELF);
		}
		UserFindDto userFindDto = modelMapperBean.modelMapper().map(userFindIn, UserFindDto.class);
		String uuid = userService.getUserUUIDStringByPhoneAndName(userFindDto);
		return ApiResponse.ofSuccess(uuid);
	}


	@Operation(summary = "아이디 중복 확인", description = "유저의 아이디 중복 확인")
	@PostMapping("/id-check")
	public ApiResponse<Void> idCheck(@RequestBody UserIdCheckIn userIdCheckIn) {
		userService.checkCanUseLoginId(userIdCheckIn.getLoginId());
		return ApiResponse.ofSuccess(null);
	}


	@Operation(summary = "비밀번호 수정", description = "로그인한 유저 비밀번호 수정")
	@PatchMapping("/pwd")
	public ApiResponse<Void> updatePassword(@RequestBody UserUpdatePwIn userUpdatePwIn, Authentication authentication) {
		UserPwDto userPwDto = modelMapperBean.modelMapper().map(userUpdatePwIn, UserPwDto.class);
		userService.updatePasswordLoginUser(userPwDto, authentication);
		return ApiResponse.ofSuccess(null);
	}


	@Operation(summary = "포인트 비밀번호 수정 및 등록", description = "포인트 비밀번호 수정, 기존에 등록하지 않았을 경우 등록 처리")
	@PatchMapping("/point-pwd")
	public ApiResponse<Void> updatePointPassword(@RequestBody UserUpdatePointPwIn userUpdatePointPwIn, Authentication authentication) {
		userService.updatePointPasswordLoginUser(userUpdatePointPwIn.getPointPw(), authentication);
		log.info(AuthUtils.getUserDetail(authentication).toString());
		return ApiResponse.ofSuccess(null);
	}

}
