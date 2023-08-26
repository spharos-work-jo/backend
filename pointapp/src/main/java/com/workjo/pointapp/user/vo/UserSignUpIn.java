package com.workjo.pointapp.user.vo;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserSignUpIn {

	private String loginId;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String address;

}