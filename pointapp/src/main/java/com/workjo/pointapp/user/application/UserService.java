package com.workjo.pointapp.user.application;


import com.workjo.pointapp.user.domain.User;
import com.workjo.pointapp.user.dto.UserFindDto;

import java.util.UUID;


public interface UserService {

	String getUserUUIDStringByPhoneAndName(UserFindDto userFindDto);
	void checkCanUseLoginId(String Id);
	User getUserByUUID(UUID uuid);

}