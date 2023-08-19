package com.workjo.pointapp.common;


import com.workjo.pointapp.config.exception.ErrorCode;


public record ApiResponse<T>(
	T data,
	String message,
	String code
) {

	public static ApiResponse<Void> ofError(ErrorCode code) {
		return new ApiResponse<>(null, code.getDescription(), code.getCode());
	}


	public static <T> ApiResponse<T> ofSuccess(T data) {
		return new ApiResponse<>(data, "성공", "S001");
	}

}