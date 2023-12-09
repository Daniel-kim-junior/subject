package com.ext.subject.util.common;


import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExtensionExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse<?> handleValidationException(final MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		Map<String, String> errorMap = new HashMap<>();
		// 필드 에러를 순회하며 메시지를 추출
		for (FieldError error : result.getFieldErrors()) {
			// 필드 이름과 에러 메시지를 맵에 추가
			errorMap.put(error.getField(), error.getDefaultMessage());
		}
		return ApiResponse.createError(errorMap);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse<?> handleHibernateViolationException(final ConstraintViolationException ex) {
		return ApiResponse.createHibernateFail(ex);
	}

	@ExceptionHandler(ExtensionNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse<String> handleExtNotFoundException(final ExtensionNotFoundException ex) {
		return ApiResponse.createError(ex.getMessage());
	}
}
