package com.workjo.pointapp.config.exception;

import com.workjo.pointapp.common.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /* Custom Exception */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ResponseDto<Void>> handleCustomException(final CustomException e) {
        log.error("handleCustomException: {}", e.getErrorCode());
        return new ResponseEntity<>(ResponseDto.ofError(e.getErrorCode()), HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

}