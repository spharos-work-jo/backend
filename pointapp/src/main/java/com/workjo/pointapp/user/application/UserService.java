package com.workjo.pointapp.user.application;


import com.workjo.pointapp.user.dto.UserFindDto;


public interface UserService {

	String getUserUUIDStringByPhoneAndName(UserFindDto userFindDto);
	void checkCanUseLoginId(String Id);

}