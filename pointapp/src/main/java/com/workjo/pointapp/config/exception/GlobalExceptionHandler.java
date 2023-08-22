package com.workjo.pointapp.config.exception;


import com.workjo.pointapp.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/* Custom Exception */
	@ExceptionHandler(CustomException.class)
	protected ResponseEntity<ApiResponse<Void>> handleCustomException(final CustomException e) {
		log.error("handleCustomException: {}", e.getErrorCode());
		return new ResponseEntity<>(ApiResponse.ofError(e.getErrorCode()), HttpStatus.valueOf(e.getErrorCode().getStatus()));
	}


	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<ApiResponse<Void>> handleHttpRequestMethodNotSupportedException() {
		return new ResponseEntity<>(ApiResponse.ofError(ErrorCode.METHOD_NOT_ALLOWED), HttpStatusCode.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()));
	}


	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<ApiResponse<Void>> handleRuntimeException(final RuntimeException e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(ApiResponse.ofError(ErrorCode.INTERNAL_SERVER_ERROR), HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
	}

}