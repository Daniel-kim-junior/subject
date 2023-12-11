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
	public ApiResponse<?> handleValidationException(final MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		String message = "";
		// 필드 에러를 순회하며 메시지를 추출
		for (FieldError error : result.getFieldErrors()) {
			// 에러 메시지 추가
			message = error.getDefaultMessage();
		}
		return ApiResponse.createError(message);
	}

	@ExceptionHandler(Exceed200EaException.class)
	public ApiResponse<?> handleExceed200Exception(final Exceed200EaException ex) {
		return ApiResponse.createError(ex.getMessage());
	}


	@ExceptionHandler(ConstraintViolationException.class)
	public ApiResponse<?> handleHibernateViolationException(final ConstraintViolationException ex) {
		return ApiResponse.createHibernateFail(ex);
	}
	@ExceptionHandler(ExtensionNotFoundException.class)
	public ApiResponse<String> handleExtNotFoundException(final ExtensionNotFoundException ex) {
		return ApiResponse.createError(ex.getMessage());
	}
}
