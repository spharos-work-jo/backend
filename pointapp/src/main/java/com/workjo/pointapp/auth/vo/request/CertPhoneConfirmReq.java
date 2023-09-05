package com.workjo.pointapp.auth.vo.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class CertPhoneConfirmReq {

	@NotBlank
	String phone;
	@NotBlank
	String certCode;

}
