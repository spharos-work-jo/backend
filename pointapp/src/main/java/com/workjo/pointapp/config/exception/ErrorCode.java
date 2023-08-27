package com.workjo.pointapp.config.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorCode {

	NOTFOUND_RESOURCE(HttpStatus.NOT_FOUND, "C001", "해당 자원이 존재하지 않습니다."),
	DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "C002", "이미 존재하는 데이터입니다."),
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C003", "Method Not Allowed"),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C004", "Internal Server Error"),
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "C011", "잘못된 요청입니다."),

	/*로그인*/
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "L001", "로그인이 필요합니다."),
	FAIL_LOGIN(HttpStatus.BAD_REQUEST, "L002", "로그인 실패"),

	/*유저*/
	NOTFOUND_USER(HttpStatus.NOT_FOUND, "U001", "존재하지 않는 유저입니다.");

	private final HttpStatus status;
	private final String code;
	private final String description;


	ErrorCode(HttpStatus status, String code, String description) {
		this.status = status;
		this.code = code;
		this.description = description;
	}
}