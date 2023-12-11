package com.ext.subject.service;

import static com.ext.subject.dto.ExtensionDto.*;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ExtensionCacheService {

	private final ExtensionService extensionService;

	public ExtensionCacheService(ExtensionService extensionService) {
		this.extensionService = extensionService;
	}


	@Cacheable(value = "FixExtensionStore", key = "'fixedListData'")
	public List<GetFixedResDto> getFixedCacheData() {
		return extensionService.readFixedExtensions();
	}

	@Cacheable(value = "CustomExtensionStore", key = "'customListData'")
	public List<GetCustomResDto> getCustomCacheData() {
		return extensionService.readCustomExtensions();
	}

	@CachePut(value = "FixExtensionStore", key = "'fixedListData'")
	public List<GetFixedResDto> refreshFixedExtensions(final List<PostFixedReqDto> dto) {
		return extensionService.createInitFixedList(dto);
	}

	@CachePut(value = "CustomExtensionStore", key = "'customListData'")
	public List<GetCustomResDto> refreshCustomExtensions(final PostCustomReqDto data) {
		return extensionService.createCustomExtension(data);
	}

	@CacheEvict(value = "FixExtensionStore", key = "'fixedListData'")
	public boolean expireFixedCacheData() {
		return true;
	}

	@CacheEvict(value = "CustomExtensionStore", key = "'customListData'")
	public boolean expireCustomCacheData() {
		return true;
	}

	@CachePut(value = "FixExtensionStore", key = "'fixedListData'")
	public List<GetFixedResDto> patchFixedCacheUpdate(final PatchFixedReqDto dto) {
		return extensionService.updateFixExtension(dto);
	}

	@CachePut(value = "CustomExtensionStore", key = "'customListData'")
	public List<GetCustomResDto> deleteCustomCacheUpdate(final DeleteCustomReqDto dto) {
		return extensionService.deleteCustomExtension(dto);
	}
}
