package com.workjo.pointapp.auth.domain;


import com.workjo.pointapp.config.exception.CustomException;
import com.workjo.pointapp.config.exception.ErrorCode;


public enum OauthProviderType {
	NAVER, KAKAO, APPLE;


	public static OauthProviderType find(String value) {
		try {
			return valueOf(value);
		} catch (IllegalArgumentException | NullPointerException e) {
			throw new CustomException(ErrorCode.BAD_REQUEST);
		}
	}
}
