package com.ext.subject.util.config;

import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.cache.transaction.TransactionAwareCacheManagerProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class LocalCacheConfig {

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
		simpleCacheManager
			.setCaches(List.of(new ConcurrentMapCache("FixExtensionStore")
				, new ConcurrentMapCache("CustomExtensionStore")));
		simpleCacheManager.initializeCaches();
		return new TransactionAwareCacheManagerProxy(simpleCacheManager);
	}

}
