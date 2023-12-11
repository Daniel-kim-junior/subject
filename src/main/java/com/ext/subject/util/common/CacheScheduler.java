package com.ext.subject.util.common;

import static lombok.AccessLevel.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ext.subject.dto.ExtensionDto;
import com.ext.subject.dto.ExtensionDto.GetCustomResDto;
import com.ext.subject.dto.ExtensionDto.GetFixedResDto;
import com.ext.subject.service.ExtensionCacheService;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor(access = PROTECTED)
public class CacheScheduler {

	private ExtensionCacheService extensionCacheService;

	protected CacheScheduler(final ExtensionCacheService extensionCacheService) {
		this.extensionCacheService = extensionCacheService;
	}

	@Scheduled(cron = "0 0 0 * * *")
	public void evictExpiredCache() {
		if(isFixedCache()) {
			extensionCacheService.expireFixedCacheData();
		}
		if(isCustomCache()) {
			extensionCacheService.expireCustomCacheData();
		}
	}

	private boolean isCustomCache() {
		List<GetCustomResDto> data = extensionCacheService.getCustomCacheData();
		if(data == null || data.size() == 0) {
			return false;
		}
		return true;
	}

	private boolean isFixedCache() {
		List<GetFixedResDto> data = extensionCacheService.getFixedCacheData();
		if(data == null || data.size() == 0) {
			return false;
		}
		return true;
	}
}
