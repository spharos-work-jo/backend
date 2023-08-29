package com.workjo.pointapp.auth.dto;


import lombok.*;


@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfoDto {

	private Long id;
	private String loginId;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String uuid;
	private String token;

}