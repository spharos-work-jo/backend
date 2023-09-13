package com.workjo.pointapp.auth;


import com.workjo.pointapp.auth.domain.UserOauth;
import com.workjo.pointapp.auth.dto.LoginDto;
import com.workjo.pointapp.auth.dto.LoginInfoDto;
import com.workjo.pointapp.auth.dto.OauthUserCreateDto;
import com.workjo.pointapp.auth.dto.OauthUserLoginDto;
import com.workjo.pointapp.auth.infrastructure.UserOauthRepository;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.config.security.JwtTokenProvider;
import com.workjo.pointapp.pointcard.application.PointCardService;
import com.workjo.pointapp.user.application.UserService;
import com.workjo.pointapp.user.domain.User;
import com.workjo.pointapp.user.dto.UserSignUpDto;
import com.workjo.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImple implements AuthService {

	private final ModelMapperBean modelMapperBean;

	private final UserRepository userRepository;
	private final UserOauthRepository userOauthRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManager authenticationManager;
	private final UserService userService;
	private final PointCardService pointCardService;


	@Override
	@Transactional
	public void signUp(UserSignUpDto userSignUpDto) {
		userService.checkCanUseLoginId(userSignUpDto.getLoginId());

		UUID uuid = UUID.randomUUID();

		User user = userSignUpDto.toEntity(uuid);
		user.encodePassword(userSignUpDto.getPassword());
		user = userRepository.save(user);
		pointCardService.createPointCardAtSignUp(uuid);
	}


	@Override
	@Transactional(readOnly = true)
	public LoginInfoDto authenticate(LoginDto loginDto) {
		User user = userRepository.findByLoginId(loginDto.getLoginId())
			.orElseThrow(() -> new CustomException(ErrorCode.FAIL_LOGIN));

		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				user.getUsername(),
				loginDto.getPassword()
			)
		);

		return this.setLoginInfoDto(user);
	}


	@Override
	@Transactional(readOnly = true)
	public LoginInfoDto oauthAuthenticate(OauthUserLoginDto oauthUserLoginDto) {
		UserOauth userOauth = userOauthRepository.findByOauthIdAndProvider(oauthUserLoginDto.getOauthId(), oauthUserLoginDto.getProvider())
			.orElseThrow(() -> new CustomException(ErrorCode.NEED_INTERGRATED_LOGIN));
		return this.setLoginInfoDto(userOauth.getUser());
	}


	@Override
	public void createUserOauth(OauthUserCreateDto oauthUserCreateDto) {
		User user = userRepository.findByUUID(oauthUserCreateDto.getUuid())
			.orElseThrow(() -> new CustomException(ErrorCode.FAIL_LOGIN));

		if (userOauthRepository.existsByOauthIdAndProvider(oauthUserCreateDto.getOauthId(), oauthUserCreateDto.getProvider())
			|| userOauthRepository.existsByUserAndProvider(user, oauthUserCreateDto.getProvider())) {
			throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
		}

		userOauthRepository.save(UserOauth.builder()
			.user(user)
			.oauthId(oauthUserCreateDto.getOauthId())
			.provider(oauthUserCreateDto.getProvider())
			.build());
	}


	/**
	 * user 정보를 LoginInfoDto로 변환
	 * AuthService 내부에서만 사용
	 *
	 * @param user 유저 객체
	 * @return LoginInfoDto 정상적으로 로그인시 response
	 */
	private LoginInfoDto setLoginInfoDto(User user) {
		String jwtToken = jwtTokenProvider.generateToken(user);
		LoginInfoDto loginInfoDto = modelMapperBean.modelMapper().map(user, LoginInfoDto.class);
		loginInfoDto.setUuid(user.getUUID().toString());
		loginInfoDto.setToken(jwtToken);
		return loginInfoDto;
	}

}