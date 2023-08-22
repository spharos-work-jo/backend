package com.workjo.pointapp.auth;


import com.workjo.pointapp.auth.vo.LoginRequest;
import com.workjo.pointapp.auth.vo.LoginResponse;
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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImple implements AuthService {

	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManager authenticationManager;
	private final UserService userService;


	@Override
	public void signUp(UserSignUpDto userSignUpDto) {
		userService.checkCanUseLoginId(userSignUpDto.getLoginId());

		UUID uuid = UUID.randomUUID();
		String uuidString = uuid.toString();

		User user = userSignUpDto.toEntity(uuidString);
		user.encodePassword(userSignUpDto.getPassword());
		userRepository.save(user);
	}


	public LoginResponse authenticate(LoginRequest loginRequest) {
		User user = userRepository.findByLoginId(loginRequest.getLoginId())
			.orElseThrow(() -> new CustomException(ErrorCode.NOTFOUND_USER));

		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				user.getUsername(),
				loginRequest.getPassword()
			)
		);

		String jwtToken = jwtTokenProvider.generateToken(user);
		return LoginResponse.builder()
			.token(jwtToken)
			.build();
	}


	public String getCurrentUserUUID(Authentication authentication) {
		String uuid;
		if (authentication == null) {
			// TODO: remove and throw new CustomException
			uuid = userRepository.findFirstUser();
		} else {
			uuid = authentication.getName();
		}
		return uuid;
	}

}