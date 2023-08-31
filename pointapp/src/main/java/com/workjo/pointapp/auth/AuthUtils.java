package com.workjo.pointapp.auth;


import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import com.workjo.pointapp.user.dto.UserGetDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;


@Slf4j
public class AuthUtils {

	private static final ModelMapper modelMapper = new ModelMapper();


	public static UUID getCurrentUserUUID(Authentication authentication) {
		UUID uuid;
		if (authentication == null) {
			throw new CustomException(ErrorCode.UNAUTHORIZED);
		} else {
			uuid = UUID.fromString(authentication.getName());
		}
		return uuid;
	}


	public static UserGetDto getCurrentUserDto(Authentication authentication) {
		UserGetDto userGetDto;
		if (authentication == null) {
			throw new CustomException(ErrorCode.UNAUTHORIZED);
		} else {
			userGetDto = modelMapper.map(authentication.getPrincipal(), UserGetDto.class);
		}
		return userGetDto;
	}


	public static UserDetails getUserDetail(Authentication authentication) {
		UserDetails userDetails;
		if (authentication == null) {
			throw new CustomException(ErrorCode.UNAUTHORIZED);
		} else {
			userDetails = (UserDetails) authentication.getPrincipal();
		}
		return userDetails;
	}

}
