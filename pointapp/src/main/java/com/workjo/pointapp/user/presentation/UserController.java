package com.workjo.pointapp.user.presentation;


import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.user.application.UserService;
import com.workjo.pointapp.user.dto.UserFindDto;
import com.workjo.pointapp.user.dto.UserSignUpDto;
import com.workjo.pointapp.user.vo.UserFindIn;
import com.workjo.pointapp.user.vo.UserIdCheckIn;
import com.workjo.pointapp.user.vo.UserSignUpIn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Tag(name = "User Controller", description = "유저 기능")
@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

	private final ModelMapperBean modelMapperBean;

	private final UserService userService;


	@Operation(summary = "선물할 유저 UUID 찾기", description = "핸드폰 번호와 실명으로 다른 유저의 UUID 조회, 선물하기에서 사용")
	@GetMapping("/find-for-gift")
	public ApiResponse<String> getUserUUIDByPhoneAndName(@RequestBody UserFindIn userFindIn) {
		UserFindDto userFindDto = modelMapperBean.modelMapper().map(userFindIn, UserFindDto.class);
		String uuid = userService.getUserUUIDByPhoneAndName(userFindDto);
		return ApiResponse.ofSuccess(uuid);
	}


	@Operation(summary = "회원가입", description = "회원가입")
	@PostMapping("/signup")
	public ApiResponse<Void> createUser(@RequestBody UserSignUpIn userSignUpIn) {
		log.info("INPUT Object Data is : {}", userSignUpIn);
		UserSignUpDto userSignUpDto = modelMapperBean.modelMapper().map(userSignUpIn, UserSignUpDto.class);
		userService.createUser(userSignUpDto);
		return ApiResponse.ofSuccess(null);
	}


	@Operation(summary = "아이디 중복 확인", description = "유저의 아이디 중복 확인")
	@GetMapping("/id-check")
	public ApiResponse<Void> idCheck(@RequestBody UserIdCheckIn userIdCheckIn) {
		userService.checkCanUseLoginId(userIdCheckIn.getLoginId());
		return ApiResponse.ofSuccess(null);
	}

}
