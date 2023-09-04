package com.workjo.pointapp.auth.vo.request;


import lombok.Getter;


@Getter
public class OauthLoginCreateReq {

	private String oauthId;
	private String provider;
	private String loginId;
	private String password;

}
