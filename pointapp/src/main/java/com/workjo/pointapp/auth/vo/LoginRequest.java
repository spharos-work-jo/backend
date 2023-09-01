package com.workjo.pointapp.auth.vo;


import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	@NotBlank
	private String loginId;
	@NotBlank
	private String password;

}