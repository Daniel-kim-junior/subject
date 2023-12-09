package com.ext.subject.service;

import static com.ext.subject.dto.ExtensionDto.*;
import static com.ext.subject.util.common.ExtensionCategory.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ext.subject.domain.Extension;
import com.ext.subject.repository.ExtensionRepository;
import com.ext.subject.util.common.Exceed200EaCustomExt;

@Service
public class ExtensionService {

	private final ExtensionRepository extensionRepository;

	private final ExtensionCacheService extensionCacheService;

	private final ExtensionLogService extensionLogService;

	protected ExtensionService(final ExtensionRepository extensionRepository,
		final ExtensionCacheService extensionCacheService,
		final ExtensionLogService extensionLogService) {
		this.extensionCacheService = extensionCacheService;
		this.extensionRepository = extensionRepository;
		this.extensionLogService = extensionLogService;
	}

	@Transactional
	public void createCustomExtension(final PostCustomReqDto dto) {
		/**
		 * validation
		 * 1. 공백을 포함하거나 영어가 아닌 문자가 포함되는 확장자 이름일 때
		 * 2. 20글자가 넘는 확장자
		 * 3. 200개가 넘을 때
		 * 4. 중복되는 확장자일 때(hibernate exception unique key)
		 * 5. 고정 확장자에 속해 있는 확장자 이름일 때
		 */
		readCustomExtensions();
		Extension extension = extensionRepository.save(dto.customDtoToExtension(dto));
		extensionCacheService.refreshCustomExtensions(readCustomExtensions());
		// extensionLogService.createExtLog(extension.makeLog(extension));
	}


	@Transactional
	public void updateFixExtension(final PatchFixedReqDto dto) {
		extensionRepository.findByName(dto.getExtName()).changeActiveStatus();
		extensionCacheService.refreshFixedExtensions(readFixedExtensions());
	}

	@Transactional
	public void deleteCustomExtension(final DeleteCustomReqDto dto) {
		extensionRepository.findByName(dto.getExtName());
		extensionCacheService.refreshCustomExtensions(readCustomExtensions());
	}

	@Transactional(readOnly = true)
	public List<GetFixedResDto> readFixedExtensions() {
		if(extensionCacheService.isEmptyFixedCache()) {
			return extensionRepository.findByCategory(FIXED)
				.stream().map(e -> e.fixExtToDto()).collect(Collectors.toList());
		}
		return extensionCacheService.getFixedCacheData().getData();
	}

	@Transactional(readOnly = true)
	@Exceed200EaCustomExt
	public List<GetCustomResDto> readCustomExtensions() {
		if(extensionCacheService.isEmptyCustomCache()) {
			return extensionRepository.findByCategory(CUSTOM)
				.stream().map(e -> e.customExtToDto()).collect(Collectors.toList());
		}
		return extensionCacheService.getCustomCacheData().getData();
	}

}
