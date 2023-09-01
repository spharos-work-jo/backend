package com.workjo.pointapp.auth.vo;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class UserSignUpIn {

	@NotBlank
	private String loginId;
	@NotBlank
	private String password;
	@NotBlank
	private String name;
	private String email;
	private String phone;
	private String address;

}