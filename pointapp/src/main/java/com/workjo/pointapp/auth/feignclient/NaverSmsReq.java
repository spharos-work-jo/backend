package com.workjo.pointapp.auth.feignclient;


import lombok.*;

import java.util.List;


@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NaverSmsReq {

	String type;
	String contentType;
	String countryCode;
	String from;
	String content;
	List<NaverSmsMessageReq> messages;

}
