package com.workjo.pointapp.user.presentation;


import com.workjo.pointapp.auth.AuthService;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.user.application.UserService;
import com.workjo.pointapp.user.dto.UserFindDto;
import com.workjo.pointapp.user.dto.UserGetDto;
import com.workjo.pointapp.user.vo.UserFindIn;
import com.workjo.pointapp.user.vo.UserIdCheckIn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "User Controller", description = "유저 기능")
@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

	private final ModelMapperBean modelMapperBean;

	private final UserService userService;
	private final AuthService authService;


	@Operation(summary = "선물할 유저 UUID 찾기", description = "핸드폰 번호와 실명으로 다른 유저의 UUID 조회, 선물하기에서 사용")
	@PostMapping("/find-for-gift")
	public ApiResponse<String> getUserUUIDByPhoneAndName(@RequestBody UserFindIn userFindIn, Authentication authentication) {
		UserGetDto userDto = authService.getCurrentUserDto(authentication);
		if (userFindIn.getName().equals(userDto.getUserName()) && userFindIn.getPhone().equals(userDto.getPhone())) {
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

}
