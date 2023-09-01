package com.workjo.pointapp.auth;


import com.workjo.pointapp.auth.feignclient.NaverSmsClient;
import com.workjo.pointapp.auth.feignclient.NaverSmsMessageReq;
import com.workjo.pointapp.auth.feignclient.NaverSmsReq;
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

	private static String SEND_TYPE_SMS = "sms";
	private final NaverSmsClient naverSmsClient;
	@Value("${external-api.naver-sms.service-id}")
	private String serviceId;
	@Value("${external-api.naver-sms.access-key}")
	private String accessKey;
	@Value("${external-api.naver-sms.secret-key}")
	private String secretKey;
	@Value("${external-api.naver-sms.from}")
	private String senderPhone;

	
	@Override
	public void sendSmsCertMessage(String receiverPhone) {
		String code = CertUtils.makeRandomNumberString();
		String smsContent = String.format("[일시켜조 포인트] \n본인확인 인증번호 [%s]를 입력해주세요.", code);

		List<NaverSmsMessageReq> messageList = new ArrayList<>();
		messageList.add(NaverSmsMessageReq.builder()
			.to(receiverPhone)
			.content(smsContent)
			.build());

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

		try {
			naverSmsClient.sendSmsMessage(serviceId, timestamp, accessKey, signatureString, request);
		} catch (FeignException e) {
			throw new CustomException(ErrorCode.EXTERNAL_NCP_SERVER_ERROR);
		}
	}

}
