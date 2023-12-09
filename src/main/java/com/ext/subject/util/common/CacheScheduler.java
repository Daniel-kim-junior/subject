package com.ext.subject.util.common;

import static lombok.AccessLevel.*;

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
		if(extensionCacheService.getFixedCacheData().getData() != null) {
			extensionCacheService.expireFixedCacheData();
		}
		if(extensionCacheService.getCustomCacheData().getData() != null) {
			extensionCacheService.expireCustomCacheData();
		}
	}


}
