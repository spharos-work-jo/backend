package com.workjo.pointapp.auth.vo.request;


import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
public class OauthLoginReq {

	private String oauthId;
	private String provider;

}
