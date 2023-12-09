package com.ext.subject.util.common;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

	private static final String SUCCESS_STATUS = "success";
	private static final String FAIL_STATUS = "fail";
	private static final String ERROR_STATUS = "error";


	private String status;

	private T data;

	private String message;

	public static <T> ApiResponse<T> createSuccess(final T data) {

		return new ApiResponse<>(SUCCESS_STATUS, data, null);
	}

	public static <T> ApiResponse<T> createSuccessNoContent() {
		return new ApiResponse<>(SUCCESS_STATUS, null, null);
	}

	// Hibernate Validator에 의해 유효하지 않은 데이터로 인해 API 호출이 거부될때 반환
	public static ApiResponse<?> createHibernateFail(final ConstraintViolationException ex) {
		Map<String, String> error = new HashMap<>();
		error.put("extName", "중복된 확장자이거나 서버에 문제가 생겼습니다");
		return new ApiResponse<>(FAIL_STATUS, error, null);

	}

	// 예외 발생으로 API 호출 실패시 반환
	public static <T> ApiResponse<T> createError(final T data) {
		return new ApiResponse<>(ERROR_STATUS, data, null);
	}


	private ApiResponse(final String status, final T data, final String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}


}




