package com.workjo.pointapp.auth;


import com.workjo.pointapp.auth.vo.LoginRequest;
import com.workjo.pointapp.auth.vo.LoginResponse;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.user.dto.UserSignUpDto;
import com.workjo.pointapp.user.vo.UserSignUpIn;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final ModelMapperBean modelMapperBean;
	private final AuthService authService;


	@PostMapping("/login")
	public ApiResponse<LoginResponse> login(
		@RequestBody LoginRequest loginRequest
	) {
		return ApiResponse.ofSuccess(authService.authenticate(loginRequest));
	}


	@Operation(summary = "회원가입", description = "회원가입")
	@PostMapping("/signup")
	public ApiResponse<Void> createUser(@RequestBody UserSignUpIn userSignUpIn) {
		log.info("INPUT Object Data is : {}", userSignUpIn);
		UserSignUpDto userSignUpDto = modelMapperBean.modelMapper().map(userSignUpIn, UserSignUpDto.class);
		authService.signUp(userSignUpDto);
		return ApiResponse.ofSuccess(null);
	}

}
