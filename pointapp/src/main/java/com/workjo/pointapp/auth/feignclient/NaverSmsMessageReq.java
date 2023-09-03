package com.workjo.pointapp.auth.feignclient;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@ToString
@Builder
@Getter
public class NaverSmsMessageReq {

	String to;
	String content;

}
