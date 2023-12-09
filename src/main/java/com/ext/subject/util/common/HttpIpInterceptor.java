package com.ext.subject.util.common;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

@Component
@Getter
public class HttpIpInterceptor implements HandlerInterceptor {

	// 적용
	private String[] headerTypes = {"X-Forwarded-For", "Proxy-Client-IP",
		"WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
	private String ip;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


		for(String headerType: headerTypes) {
			ip = request.getHeader(headerType);
			if(ip != null) break;
		}

		if (ip == null) ip = request.getRemoteAddr();

		return true;
	}



}
