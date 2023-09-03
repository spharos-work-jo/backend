package com.workjo.pointapp.auth;


import com.workjo.pointapp.auth.domain.PhoneCertCode;
import com.workjo.pointapp.auth.dto.CertPhoneDto;
import com.workjo.pointapp.auth.feignclient.NaverSmsClient;
import com.workjo.pointapp.auth.feignclient.NaverSmsMessageReq;
import com.workjo.pointapp.auth.feignclient.NaverSmsReq;
import com.workjo.pointapp.auth.infrastructure.PhoneCertCodeRedisRepository;
import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class CertServiceImple implements CertService {

	private final NaverSmsClient naverSmsClient;
	private final PhoneCertCodeRedisRepository phoneCertCodeRedisRepository;
	@Value("${external-api.naver-sms.service-id}")
	private String serviceId;
	@Value("${external-api.naver-sms.access-key}")
	private String accessKey;
	@Value("${external-api.naver-sms.secret-key}")
	private String secretKey;
	@Value("${external-api.naver-sms.from}")
	private String senderPhone;


	/**
	 * 휴대폰 인증번호 발송
	 */
	@Override
	public void sendSmsCertCodeMessage(String receiverPhone) {
		String code = CertUtils.makeRandomNumberString();   // 6자리 랜덤 인증번호 생성

		/* redis에 인증번호 저장 */
		phoneCertCodeRedisRepository.save(PhoneCertCode.builder()
			.phone(receiverPhone)
			.certCode(code)
			.build());

		/* make ncp sms request */
		String smsContent = String.format("[일시켜조 포인트] \n본인확인 인증번호 [%s]를 입력해주세요.", code);

		List<NaverSmsMessageReq> messageList = new ArrayList<>();
		messageList.add(NaverSmsMessageReq.builder()
			.to(receiverPhone)
			.content(smsContent)
			.build());

		String SEND_TYPE_SMS = "sms";
		NaverSmsReq request = NaverSmsReq.builder()
			.contentType("COMM")
			.countryCode("82")
			.type(SEND_TYPE_SMS)
			.from(senderPhone)
			.content("[일시켜조 포인트]")
			.messages(messageList)
			.build();

		String timestamp = Long.toString(System.currentTimeMillis());
		String signatureString;
		try {
			signatureString = CertUtils.makeSendCertSmsSignature(timestamp, serviceId, accessKey, secretKey);
		} catch (Exception e) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}

		/* send sms message using FeignClient */
		try {
			naverSmsClient.sendSmsMessage(serviceId, timestamp, accessKey, signatureString, request);
		} catch (FeignException e) {
			throw new CustomException(ErrorCode.EXTERNAL_NCP_SERVER_ERROR);
		}
	}


	/**
	 * 휴대폰 인증번호 확인
	 */
	@Override
	public void confirmSmsCertCodeMessage(CertPhoneDto certPhoneDto) {
		String code = certPhoneDto.getCertCode();
		String phone = certPhoneDto.getPhone();

		/* redis에 저장된 인증번호 확인 */
		PhoneCertCode phoneCertCode = phoneCertCodeRedisRepository.findById(phone)
			.orElseThrow(() -> new CustomException(ErrorCode.CERT_CODE_EXPIRED));   // 인증번호가 만료되었거나 존재하지 않는 경우
		if (!phoneCertCode.getCertCode().equals(code)) throw new CustomException(ErrorCode.CERT_CODE_INVALID);  // 인증번호가 일치하지 않는 경우

		/* redis에 저장된 인증번호 삭제 */
		phoneCertCodeRedisRepository.delete(phoneCertCode);
	}

}
