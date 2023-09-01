package com.workjo.pointapp.auth.dto;


import lombok.*;


@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CertPhoneDto {

	private String phone;
	private String certCode;

}
