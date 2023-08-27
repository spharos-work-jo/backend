package com.workjo.pointapp.auth.vo;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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