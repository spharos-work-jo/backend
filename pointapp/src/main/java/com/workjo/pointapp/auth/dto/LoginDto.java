package com.workjo.pointapp.auth.dto;


import lombok.*;


@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

	private String loginId;
	private String password;

}
