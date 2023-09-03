package com.workjo.pointapp.auth;


import com.workjo.pointapp.auth.dto.LoginInfoDto;
import com.workjo.pointapp.auth.vo.LoginRequest;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.config.security.JwtTokenProvider;
import com.workjo.pointapp.user.application.UserService;
import com.workjo.pointapp.user.domain.User;
import com.workjo.pointapp.user.dto.UserSignUpDto;
import com.workjo.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImple implements AuthService {

	private final ModelMapperBean modelMapperBean;

	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManager authenticationManager;
	private final UserService userService;


	@Override
	public void signUp(UserSignUpDto userSignUpDto) {
		userService.checkCanUseLoginId(userSignUpDto.getLoginId());

		UUID uuid = UUID.randomUUID();

		User user = userSignUpDto.toEntity(uuid);
		user.encodePassword(userSignUpDto.getPassword());
		userRepository.save(user);
	}


	public LoginInfoDto authenticate(LoginRequest loginRequest) {
		User user = userRepository.findByLoginId(loginRequest.getLoginId())
			.orElseThrow(() -> new CustomException(ErrorCode.FAIL_LOGIN));

		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				user.getUsername(),
				loginRequest.getPassword()
			)
		);

		String jwtToken = jwtTokenProvider.generateToken(user);
		LoginInfoDto loginInfoDto = modelMapperBean.modelMapper().map(user, LoginInfoDto.class);
		loginInfoDto.setUuid(user.getUUID().toString());
		loginInfoDto.setToken(jwtToken);
		return loginInfoDto;
	}

}