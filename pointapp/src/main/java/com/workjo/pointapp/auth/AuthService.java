package com.workjo.pointapp.auth;


import com.workjo.pointapp.auth.vo.LoginRequest;
import com.workjo.pointapp.auth.vo.LoginResponse;
import com.workjo.pointapp.user.dto.UserSignUpDto;


public interface AuthService {

	void signUp(UserSignUpDto userSignUpDto);
	LoginResponse authenticate(LoginRequest loginRequest);

}
