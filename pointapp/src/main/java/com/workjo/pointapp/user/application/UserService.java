package com.workjo.pointapp.user.application;


import com.workjo.pointapp.user.domain.User;
import com.workjo.pointapp.user.dto.UserFindDto;
import com.workjo.pointapp.user.dto.UserPwDto;
import org.springframework.security.core.Authentication;

import java.util.UUID;


public interface UserService {

	String getUserUUIDStringByPhoneAndName(UserFindDto userFindDto);
	void checkCanUseLoginId(String Id);
	User getUserByUUID(UUID uuid);
	void updatePasswordLoginUser(UserPwDto userPwDto, Authentication authentication);

	void updatePointPasswordLoginUser(String pointPw, Authentication authentication);
	Boolean softDeleteUserByUUID(UUID uuid);

}