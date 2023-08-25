package com.workjo.pointapp.auth.vo;


import lombok.*;


@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	private String loginId;
	private String password;

}