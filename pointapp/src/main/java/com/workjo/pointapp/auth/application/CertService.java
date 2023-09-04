package com.workjo.pointapp.auth.application;


import com.workjo.pointapp.auth.dto.CertPhoneDto;


public interface CertService {

	void sendSmsCertCodeMessage(String receiverPhone);
	void confirmSmsCertCodeMessage(CertPhoneDto certPhoneDto);

}
