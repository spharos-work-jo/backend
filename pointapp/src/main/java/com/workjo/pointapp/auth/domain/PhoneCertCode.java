package com.workjo.pointapp.auth.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@RedisHash(value = "phoneCertCode", timeToLive = 300)
public class PhoneCertCode {

	@Id
	private String phone;
	private String certCode;

}
