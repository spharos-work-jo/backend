package com.workjo.pointapp.auth.feignclient;


import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "naverSms", url = "https://sens.apigw.ntruss.com/sms/v2", configuration = FeignConfig.class)
public interface NaverSmsClient {

	@PostMapping("/services/{serviceId}/messages")
	@Headers("Content-Type: application/json; charset=utf-8")
	NaverSmsRes sendSmsMessage(
		@PathVariable("serviceId") String serviceId,
		@RequestHeader("x-ncp-apigw-timestamp") String timestamp,
		@RequestHeader("x-ncp-iam-access-key") String accessKey,
		@RequestHeader("x-ncp-apigw-signature-v2") String signature,
		@RequestBody NaverSmsReq naverSmsReq
	);

}
