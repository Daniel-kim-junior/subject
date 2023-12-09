package com.ext.subject.service;

import static com.ext.subject.dto.ExtensionDto.*;
import static com.ext.subject.util.common.ExtensionCategory.*;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ext.subject.dto.ExtensionDto;
import com.ext.subject.repository.ExtensionRepository;

@Service
public class ExtensionService {

	private ExtensionRepository extensionRepository;

	private ExtensionCacheService extensionCacheService;

	private ExtensionLogService extensionLogService;

	public ExtensionService(ExtensionRepository extensionRepository,
		ExtensionCacheService extensionCacheService,
		ExtensionLogService extensionLogService) {
		this.extensionCacheService = extensionCacheService;
		this.extensionRepository = extensionRepository;
		this.extensionLogService = extensionLogService;
	}

	@Transactional
	public void createCustomExtension(PostCustomReqDto dto) {
		extensionRepository.save(dto.customDtoToExtension(dto));
		extensionCacheService.refreshCustomExtensions(readCustomExtensions());
	}


	@Transactional
	public void updateFixExtension(PatchFixedReqDto dto) {
		extensionRepository.findByName(dto.getExtName()).changeActiveStatus();
		extensionCacheService.refreshFixedExtensions(readFixedExtensions());
	}

	@Transactional
	public void deleteCustomExtension(DeleteCustomReqDto dto) {
		extensionRepository.findByName(dto.getExtName());
		extensionCacheService.refreshCustomExtensions(readCustomExtensions());
	}

	@Transactional(readOnly = true)
	public List<GetFixedResDto> readFixedExtensions() {
		if(extensionCacheService.isEmptyFixedCache()) {
			return extensionRepository.findByCategoryFixed(FIXED.name());
		}
		return extensionCacheService.getFixedCacheData().getData();
	}

	@Transactional(readOnly = true)
	public List<GetCustomResDto> readCustomExtensions() {
		if(extensionCacheService.isEmptyCustomCache()) {
			return extensionRepository.findByCategoryCustom(CUSTOM.name());
		}
		return extensionCacheService.getCustomCacheData().getData();
	}
}
