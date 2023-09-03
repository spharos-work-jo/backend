package com.workjo.pointapp.user.vo;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;


@Getter
public class UserFindIn {

	@NotEmpty
	private String phone;
	@NotEmpty
	private String name;

}
