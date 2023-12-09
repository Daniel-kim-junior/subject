package com.ext.subject.util.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final HttpIpInterceptor httpIpInterceptor;

	public WebConfig(HttpIpInterceptor httpIpInterceptor) {
		this.httpIpInterceptor = httpIpInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(httpIpInterceptor)
			.excludePathPatterns("/api-v1/excl/**"); // 특정 경로 제외
	}
}