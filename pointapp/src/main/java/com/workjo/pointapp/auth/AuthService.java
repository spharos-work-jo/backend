package com.workjo.pointapp.auth;


import com.workjo.pointapp.auth.dto.LoginDto;
import com.workjo.pointapp.auth.dto.LoginInfoDto;
import com.workjo.pointapp.auth.dto.OauthUserCreateDto;
import com.workjo.pointapp.auth.vo.request.OauthLoginReq;
import com.workjo.pointapp.user.dto.UserSignUpDto;


public interface AuthService {

	void signUp(UserSignUpDto userSignUpDto);
	LoginInfoDto authenticate(LoginDto loginDto);
	LoginInfoDto oauthAuthenticate(OauthLoginReq oauthloginRequest);

	void createUserOauth(OauthUserCreateDto oauthUserCreateDto);

}
