package com.workjo.pointapp.auth.presentation;


import com.workjo.pointapp.auth.AuthService;
import com.workjo.pointapp.auth.domain.OauthProviderType;
import com.workjo.pointapp.auth.dto.LoginDto;
import com.workjo.pointapp.auth.dto.LoginInfoDto;
import com.workjo.pointapp.auth.dto.OauthUserCreateDto;
import com.workjo.pointapp.auth.dto.OauthUserLoginDto;
import com.workjo.pointapp.auth.vo.LoginResponse;
import com.workjo.pointapp.auth.vo.UserSignUpIn;
import com.workjo.pointapp.auth.vo.request.LoginRequest;
import com.workjo.pointapp.auth.vo.request.OauthLoginCreateReq;
import com.workjo.pointapp.auth.vo.request.OauthLoginReq;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.user.dto.UserSignUpDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final ModelMapperBean modelMapperBean;
	private final AuthService authService;


	@PostMapping("/login")
	public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
		LoginDto loginDto = modelMapperBean.modelMapper().map(loginRequest, LoginDto.class);
		LoginInfoDto loginInfoDto = authService.authenticate(loginDto);
		return ApiResponse.ofSuccess(modelMapperBean.modelMapper().map(loginInfoDto, LoginResponse.class));
	}


	@Operation(summary = "회원가입", description = "회원가입")
	@PostMapping("/signup")
	public ApiResponse<Void> createUser(@Valid @RequestBody UserSignUpIn userSignUpIn) {
		log.debug("INPUT Object Data is : {}", userSignUpIn);
		UserSignUpDto userSignUpDto = modelMapperBean.modelMapper().map(userSignUpIn, UserSignUpDto.class);
		authService.signUp(userSignUpDto);
		return ApiResponse.ofSuccess(null);
	}


	@Operation(summary = "간편로그인", description = "간편로그인, provider : NAVER, KAKAO, APPLE (대문자 필수)")
	@PostMapping("/oauth-login")
	public ApiResponse<LoginResponse> oauthLogin(@RequestBody OauthLoginReq oauthLoginReq) {
		OauthProviderType provider = OauthProviderType.find(oauthLoginReq.getProvider());
		if (provider == null) {
			throw new CustomException(ErrorCode.BAD_REQUEST);
		}
		OauthUserLoginDto oauthUserLoginDto = modelMapperBean.modelMapper().map(oauthLoginReq, OauthUserLoginDto.class);
		LoginInfoDto loginInfoDto = authService.oauthAuthenticate(oauthUserLoginDto);
		return ApiResponse.ofSuccess(modelMapperBean.modelMapper().map(loginInfoDto, LoginResponse.class));
	}


	@Operation(summary = "통합로그인 및 간편로그인 연동", description = "간편로그인 연동되지 않은 유저의 통합로그인시 사용, provider : NAVER, KAKAO, APPLE (대문자 필수)")
	@PostMapping("/oauth-login-create")
	public ApiResponse<LoginResponse> oauthLoginCreate(@RequestBody OauthLoginCreateReq oauthLoginCreateReq) {
		OauthProviderType provider = OauthProviderType.find(oauthLoginCreateReq.getProvider());
		if (provider == null) {
			throw new CustomException(ErrorCode.BAD_REQUEST);
		}

		// 통합로그인
		LoginDto loginDto = modelMapperBean.privateStrictModelMapper().map(oauthLoginCreateReq, LoginDto.class);
		LoginInfoDto loginInfoDto = authService.authenticate(loginDto);

		// 통합로그인 성공시 간편로그인 정보 저장
		OauthUserCreateDto oauthUserCreateDto = modelMapperBean.privateStrictModelMapper().map(oauthLoginCreateReq, OauthUserCreateDto.class);
		oauthUserCreateDto.setUuid(UUID.fromString(loginInfoDto.getUuid()));
		authService.createUserOauth(oauthUserCreateDto);
		return ApiResponse.ofSuccess(modelMapperBean.modelMapper().map(loginInfoDto, LoginResponse.class));
	}

}
