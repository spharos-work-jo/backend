package com.workjo.pointapp.user.vo;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;


@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserGetOut {

	private String loginId;
	private String userName;
	private String email;
	private String phone;
	private String address;

}