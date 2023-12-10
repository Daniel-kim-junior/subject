package com.ext.subject.util.common;

import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
		if(isExpiredFixedCache()) {
			extensionCacheService.expireFixedCacheData();
		}
		if(isExpiredCustomCache()) {
			extensionCacheService.expireCustomCacheData();
		}
	}

	private boolean isExpiredCustomCache() {
		return extensionCacheService.getCustomCacheData()
			.getExpirationDate().compareTo(LocalDateTime.now()) > 0;
	}

	private boolean isExpiredFixedCache() {
		return extensionCacheService.getFixedCacheData()
			.getExpirationDate().compareTo(LocalDateTime.now()) > 0;
	}
}
