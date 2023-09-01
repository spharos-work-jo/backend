package com.workjo.pointapp.auth.feignclient;


import lombok.ToString;


@ToString
public class NaverSmsRes {

	private String requestId;
	private String requestTime;
	private String statusCode;
	private String statusName;

}
