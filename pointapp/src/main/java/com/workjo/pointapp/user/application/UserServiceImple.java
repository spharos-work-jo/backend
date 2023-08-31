package com.workjo.pointapp.user.application;


import com.workjo.pointapp.auth.AuthUtils;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.user.domain.User;
import com.workjo.pointapp.user.dto.UserFindDto;
import com.workjo.pointapp.user.dto.UserPwDto;
import com.workjo.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImple implements UserService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;


	@Override
	public String getUserUUIDStringByPhoneAndName(UserFindDto userFindDto) {
		User user = userRepository.findByPhoneAndName(userFindDto.getPhone(), userFindDto.getName())
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
		return user.getUUID().toString();
	}


	@Override
	public void checkCanUseLoginId(String loginId) {
		if (userRepository.existsByLoginId(loginId)) throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
	}


	@Override
	public User getUserByUUID(UUID uuid) {
		return userRepository.findByUUID(uuid).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));
	}


	@Override
	@Transactional
	public void updatePasswordLoginUser(UserPwDto userPwDto, Authentication authentication) {
		UserDetails userDetails = AuthUtils.getUserDetail(authentication);
		if (!passwordEncoder.matches(userPwDto.getPwd(), userDetails.getPassword())) {
			throw new CustomException(ErrorCode.BAD_REQUEST);
		}
		User user = userRepository.findByUUID(UUID.fromString(userDetails.getUsername())).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));
		user.encodePassword(userPwDto.getNewPwd());
	}


	@Override
	@Transactional
	public void updatePointPasswordLoginUser(String pointPw, Authentication authentication) {
		if (pointPw.length() != 4) throw new CustomException(ErrorCode.BAD_REQUEST);
		
		UUID uuid = AuthUtils.getCurrentUserUUID(authentication);
		User user = userRepository.findByUUID(uuid).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));
		user.encodePointPassword(pointPw);
	}

}