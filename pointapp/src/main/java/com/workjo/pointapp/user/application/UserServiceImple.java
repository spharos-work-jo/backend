package com.workjo.pointapp.user.application;


import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.user.domain.User;
import com.workjo.pointapp.user.dto.UserFindDto;
import com.workjo.pointapp.user.dto.UserSignUpDto;
import com.workjo.pointapp.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImple implements UserService {

	private final UserRepository userRepository;


	@Override
	public void createUser(UserSignUpDto userSignUpDto) {
		checkCanUseLoginId(userSignUpDto.getLoginId());

		UUID uuid = UUID.randomUUID();
		String uuidString = uuid.toString();

		User user = userSignUpDto.toEntity(uuidString);

		user.initUUID(uuidString);
		userRepository.save(user);
	}


	@Override
	public String getUserUUIDByPhoneAndName(UserFindDto userFindDto) {
		User user = userRepository.findByPhoneAndName(userFindDto.getPhone(), userFindDto.getName())
			.orElseThrow(() -> new CustomException(ErrorCode.NOTFOUND_USER));
		return user.getUUID();
	}


	@Override
	public void checkCanUseLoginId(String loginId) {
		if (userRepository.existsByLoginId(loginId)) throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
	}

}