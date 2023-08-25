package com.workjo.pointapp.auth;


import com.workjo.pointapp.auth.vo.LoginRequest;
import com.workjo.pointapp.auth.vo.LoginResponse;
import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.config.security.JwtTokenProvider;
import com.workjo.pointapp.user.application.UserService;
import com.workjo.pointapp.user.domain.User;
import com.workjo.pointapp.user.dto.UserGetDto;
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


	public UUID getCurrentUserUUID(Authentication authentication) {
		UUID uuid;
		if (authentication == null) {
			// TODO: remove and throw new CustomException
			uuid = userRepository.findFirstUser().orElseThrow().getUUID();
		} else {
			uuid = UUID.fromString(authentication.getName());
		}
		return uuid;
	}


	public UserGetDto getCurrentUserDto(Authentication authentication) {
		UserGetDto userGetDto;
		if (authentication == null) {
			// TODO: remove and throw CustomException
			userGetDto = modelMapperBean.modelMapper().map(
				userRepository.findFirstUser()
					.orElseThrow(() -> new CustomException(ErrorCode.NOTFOUND_RESOURCE)), UserGetDto.class);
		} else {
			userGetDto = modelMapperBean.modelMapper().map(authentication.getDetails(), UserGetDto.class);
		}
		return userGetDto;
	}

}