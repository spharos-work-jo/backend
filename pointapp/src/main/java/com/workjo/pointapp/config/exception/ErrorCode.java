package com.workjo.pointapp.config.exception;


import lombok.Getter;


@Getter
public enum ErrorCode {

	NOTFOUND_RESOURCE(404, "C001", "해당 자원이 존재하지 않습니다."),
	DUPLICATE_RESOURCE(409, "C002", "이미 존재하는 데이터입니다."),
	METHOD_NOT_ALLOWED(405, "C003", "Method Not Allowed"),
	INTERNAL_SERVER_ERROR(500, "C004", "Internal Server Error"),

	INVALID_REQUEST(400, "C011", "잘못된 요청입니다."),
	UNKNOWN_ERROR(400, "C012", "알 수 없는 에러"),

	UNAUTHORIZED(400, "U001", "로그인이 필요합니다."),

	INVALID_TOKEN(401, "U002", "잘못된 토큰입니다."),
	;

	private final int status;
	private final String code;
	private final String description;


	ErrorCode(int status, String code, String description) {
		this.status = status;
		this.code = code;
		this.description = description;
	}
}