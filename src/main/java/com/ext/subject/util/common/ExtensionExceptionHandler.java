package com.ext.subject.util.common;

import javax.validation.ValidationException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExtensionExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse<String> handleValidationException(final ValidationException ex) {
		return ApiResponse.createError(ex.getMessage());
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
