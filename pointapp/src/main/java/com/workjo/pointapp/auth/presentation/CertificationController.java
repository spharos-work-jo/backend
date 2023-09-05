package com.workjo.pointapp.auth.presentation;


import com.workjo.pointapp.auth.application.CertService;
import com.workjo.pointapp.auth.dto.CertPhoneDto;
import com.workjo.pointapp.auth.vo.request.CertPhoneConfirmReq;
import com.workjo.pointapp.auth.vo.request.CertPhoneReq;
import com.workjo.pointapp.common.ApiResponse;
import com.workjo.pointapp.config.ModelMapperBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Certification Controller", description = "휴대폰 인증")
@Slf4j
@RestController
@RequestMapping("/api/v1/cert")
@RequiredArgsConstructor
public class CertificationController {

	private final ModelMapperBean modelMapperBean;

	private final CertService certService;


	@Operation(summary = "휴대폰 인증번호 보내기")
	@PostMapping("/phone")
	public ApiResponse<Void> sendPhoneSms(@Valid @RequestBody CertPhoneReq certPhoneReq) {
		certService.sendSmsCertCodeMessage(certPhoneReq.getPhone());
		return ApiResponse.ofSuccess(null);
	}


	@Operation(summary = "휴대폰 인증번호 확인")
	@PostMapping("/phone/confirm")
	public ApiResponse<Void> confirmPhoneSms(@Valid @RequestBody CertPhoneConfirmReq certPhoneConfirmReq) {
		CertPhoneDto certPhoneDto = modelMapperBean.modelMapper().map(certPhoneConfirmReq, CertPhoneDto.class);
		certService.confirmSmsCertCodeMessage(certPhoneDto);
		return ApiResponse.ofSuccess(null);
	}

}
