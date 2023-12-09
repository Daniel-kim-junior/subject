package com.ext.subject.service;

import static com.ext.subject.dto.ExtensionDto.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ext.subject.repository.ExtensionLogRepository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor(access = PROTECTED)
public class ExtensionCacheService {

	private static final FixedListCacheData EMPTY_FIXED_CACHE = new FixedListCacheData();

	private static final CustomListCacheData EMPTY_CUSTOM_CACHE = new CustomListCacheData();

	@Cacheable(value = "FixExtensionStore", key = "'fixedListData'")
	public FixedListCacheData getFixedCacheData() {
		return EMPTY_FIXED_CACHE;
	}

	public boolean isEmptyFixedCache() {
		return getFixedCacheData() == EMPTY_FIXED_CACHE ? true : false;
	}

	public boolean isEmptyCustomCache() {
		return getCustomCacheData() == EMPTY_CUSTOM_CACHE ? true : false;
	}

	@CachePut(value = "FixExtensionStore", key = "'fixedListData'")
	public FixedListCacheData refreshFixedExtensions(final List<GetFixedResDto> data) {
		return FixedListCacheData.builder()
			.data(data)
			.expirationDate(LocalDateTime.now().plusDays(10))
			.build();
	}

	@CachePut(value = "CustomExtensionStore", key = "'customListData'")
	public CustomListCacheData refreshCustomExtensions(final List<GetCustomResDto> data) {
		return CustomListCacheData.builder()
			.data(data)
			.expirationDate(LocalDateTime.now().plusDays(10))
			.build();

	}

	@CacheEvict(value = "FixExtensionStore", key = "'fixedListData'")
	public boolean expireFixedCacheData() {
		return true;
	}

	@CacheEvict(value = "CustomExtensionStore", key = "'customListData")
	public boolean expireCustomCacheData() {
		return true;
	}

	@Cacheable(value = "CustomExtensionStore", key = "'customListData'")
	public CustomListCacheData getCustomCacheData() {
		return EMPTY_CUSTOM_CACHE;
	}
}
