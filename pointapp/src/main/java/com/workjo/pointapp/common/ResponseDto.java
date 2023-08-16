package com.workjo.pointapp.common;

import com.workjo.pointapp.config.exception.ErrorCode;


public record ResponseDto<T> (
        T data,
        String message,
        String code
){
    public static ResponseDto<Void> ofError(ErrorCode code) {
        return new ResponseDto<>(null, code.getDescription(), code.getCode());
    }

    public static <T> ResponseDto<T> ofSuccess(T data) {
        return new ResponseDto<>(data, "성공", "S001");
    }

}