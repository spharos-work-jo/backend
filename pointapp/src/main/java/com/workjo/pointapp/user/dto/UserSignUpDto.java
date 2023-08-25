package com.workjo.pointapp.user.dto;


import com.workjo.pointapp.user.domain.User;
import lombok.*;

import java.util.UUID;


@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpDto {

	private String loginId;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String address;


	public User toEntity(UUID uuid) {
		return User.builder()
			.loginId(this.getLoginId())
			.UUID(uuid)
			.name(this.getName())
			.password(this.getPassword())
			.email(this.getEmail())
			.phone(this.getPhone())
			.address(this.getAddress())
			.accountUse(true)
			.agreement3rd(false)
			.build();
	}

}