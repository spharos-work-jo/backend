package com.workjo.pointapp.user.dto;


import com.workjo.pointapp.user.domain.User;
import lombok.*;


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


	public User toEntity(String uuidString) {
		return User.builder()
			.loginId(this.getLoginId())
			.UUID(uuidString)
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