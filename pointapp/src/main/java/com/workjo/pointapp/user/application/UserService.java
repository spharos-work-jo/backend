package com.workjo.pointapp.user.application;


import com.workjo.pointapp.user.dto.UserFindDto;
import com.workjo.pointapp.user.dto.UserSignUpDto;


public interface UserService {

	void createUser(UserSignUpDto userSignUpDto);
	String getUserUUIDByPhoneAndName(UserFindDto userFindDto);
	void checkCanUseLoginId(String Id);

}