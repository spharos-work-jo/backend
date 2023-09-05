package com.workjo.pointapp.auth.vo.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class CertPhoneReq {

	@NotBlank
	String phone;

}
