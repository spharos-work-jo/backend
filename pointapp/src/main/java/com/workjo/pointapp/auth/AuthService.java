package com.workjo.pointapp.auth;


import com.workjo.pointapp.admin.domain.Admin;
import com.workjo.pointapp.admin.infrastructure.AdminRepository;
import com.workjo.pointapp.auth.domain.Member;
import com.workjo.pointapp.auth.vo.LoginRequest;
import com.workjo.pointapp.auth.vo.LoginResponse;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.config.security.JwtTokenProvider;
import com.workjo.pointapp.user.domain.User;
import com.workjo.pointapp.user.dto.UserSignUpDto;
import com.workjo.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final AdminRepository adminRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManager authenticationManager;


	public LoginResponse signup(UserSignUpDto userSignUpDto) {
		UUID uuid = UUID.randomUUID();
		String uuidString = uuid.toString();

		log.info("userSignUpDto is : {}", userSignUpDto);

		User user = User.builder()
			.loginId(userSignUpDto.getLoginId())
			.UUID(uuidString)
			.name(userSignUpDto.getName())
			.password(userSignUpDto.getPassword())
			.email(userSignUpDto.getEmail())
			.phone(userSignUpDto.getPhone())
			.address(userSignUpDto.getAddress())
			.build();
		user.encodePassword(user.getPassword());
		userRepository.save(user);

		String JwtToken = jwtTokenProvider.generateToken(user);
		log.info("JwtToken is : {}", JwtToken);
		return LoginResponse.builder()
			.token(JwtToken)
			.build();
	}


	public LoginResponse authenticate(LoginRequest loginRequest) {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				loginRequest.getLoginId(),
				loginRequest.getPassword()
			)
		);

		Member member;
		Optional<User> user = userRepository.findByLoginId(loginRequest.getLoginId());
		Optional<Admin> admin = adminRepository.findByLoginId(loginRequest.getLoginId());

		if (user.isEmpty() && admin.isEmpty()) throw new CustomException(ErrorCode.NOTFOUND_USER);
		else if (user.isPresent()) {
			member = user.orElseThrow(() -> new CustomException(ErrorCode.UNKNOWN_ERROR));
		} else {
			member = admin.orElseThrow(() -> new CustomException(ErrorCode.UNKNOWN_ERROR));
		}
		String jwtToken = jwtTokenProvider.generateToken(member);
		return LoginResponse.builder()
			.token(jwtToken)
			.build();
	}

}