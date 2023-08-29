package com.workjo.pointapp.auth.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

	private String token;
	private String email;
	private String name;
	private String uuid;

}
