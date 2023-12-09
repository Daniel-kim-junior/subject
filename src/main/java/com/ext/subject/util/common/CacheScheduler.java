package com.ext.subject.util.common;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ext.subject.service.ExtensionCacheService;

@Component
public class CacheScheduler {

	private final ExtensionCacheService extensionCacheService;

	protected CacheScheduler(final ExtensionCacheService extensionCacheService) {
		this.extensionCacheService = extensionCacheService;
	}

	@Scheduled(cron = "0 0 0 * * *")
	public void evictExpiredCache() {
		if(extensionCacheService.getFixedCacheData().getData() != null) {
			extensionCacheService.expireFixedCacheData();
		}
		if(extensionCacheService.getCustomCacheData().getData() != null) {
			extensionCacheService.expireCustomCacheData();
		}
	}


}
