package com.workjo.pointapp.common.domain;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@Getter
@AllArgsConstructor
@ToString
public class Member extends BaseDateTime {

	@Id
	private Long id;
	private String loginId;
	private String UUID;
	private String password;
	private String name;

	private Role role;


	public Member() {
		this.role = Role.USER;
	}

}