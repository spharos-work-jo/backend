package com.workjo.pointapp.auth;


import com.workjo.pointapp.auth.vo.LoginRequest;
import com.workjo.pointapp.auth.vo.LoginResponse;
import com.workjo.pointapp.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthControiller {

	private final AuthService authService;


	@PostMapping("/login")
	public ApiResponse<LoginResponse> login(
		@RequestBody LoginRequest loginRequest
	) {
		return ApiResponse.ofSuccess(authService.authenticate(loginRequest));
	}

}
