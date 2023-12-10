package com.ext.subject.service;

import static com.ext.subject.dto.ExtensionDto.*;
import static com.ext.subject.util.common.ExtReqType.*;
import static com.ext.subject.util.common.ExtensionCategory.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ext.subject.domain.Extension;
import com.ext.subject.repository.ExtensionLogRepository;
import com.ext.subject.repository.ExtensionRepository;
import com.ext.subject.util.common.Exceed200EaCustomExt;
import com.ext.subject.util.common.ExtReqType;
import com.ext.subject.util.common.ExtensionNotFoundException;
import com.ext.subject.util.common.HttpIpInterceptor;

@Service
public class ExtensionService {

	private final ExtensionRepository extensionRepository;

	private final ExtensionCacheService extensionCacheService;

	private final ExtensionLogService extensionLogService;

	private final HttpIpInterceptor httpIpInterceptor;

	private static final String NOT_FOUND_EXT = "삭제 하려는 확장자가 존재하지 않습니다";

	protected ExtensionService(final ExtensionRepository extensionRepository,
		final ExtensionCacheService extensionCacheService,
		final HttpIpInterceptor httpIpInterceptor,
		final ExtensionLogService extensionLogService) {
		this.extensionCacheService = extensionCacheService;
		this.extensionRepository = extensionRepository;
		this.httpIpInterceptor = httpIpInterceptor;
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
		Extension extension = extensionRepository.save(dto.customDtoToExtension());
		refreshCache();
		createLog(extension,CREATE);
	}

	@Transactional
	public void updateFixExtension(final PatchFixedReqDto dto) {
		Extension extension = extensionRepository.findByName(dto.getExtName())
			.orElseThrow(() -> new ExtensionNotFoundException(NOT_FOUND_EXT));
		extension.changeActiveStatus();
		refreshCache();
		createLog(extension, extension.getFixedLogType());
	}

	@Transactional
	public void deleteCustomExtension(final DeleteCustomReqDto dto) {
		Extension extension = extensionRepository.findByName(dto.getExtName())
			.orElseThrow(() -> new ExtensionNotFoundException(NOT_FOUND_EXT));
		extensionRepository.delete(extension);
		refreshCache();
		createLog(extension, DELETE);
	}

	@Transactional(readOnly = true)
	public List<GetFixedResDto> readFixedExtensions() {
		if (extensionCacheService.isEmptyFixedCache()) {
			return extensionRepository.findByCategory(FIXED)
				.stream().map(e -> e.fixExtToDto()).collect(Collectors.toList());
		}
		return extensionCacheService.getFixedCacheData().getData();
	}

	@Transactional(readOnly = true)
	@Exceed200EaCustomExt
	public List<GetCustomResDto> readCustomExtensions() {
		if (extensionCacheService.isEmptyCustomCache()) {
			return extensionRepository.findByCategory(CUSTOM)
				.stream().map(e -> e.customExtToDto()).collect(Collectors.toList());
		}
		return extensionCacheService.getCustomCacheData().getData();
	}

	@Transactional
	public void createInitFixedList(List<PostFixedReqDto> list) {
		list.stream().forEach(this::proceedSave);
		refreshCache();
	}

	private void createLog(Extension extension, ExtReqType type) {
		extensionLogService.createExtLog(extension.makeLog(extension, httpIpInterceptor.getIp(), type));
	}

	private void refreshCache() {
		extensionCacheService.refreshFixedExtensions(readFixedExtensions());
	}

	private void proceedSave(PostFixedReqDto e) {
		Extension extension = extensionRepository.save(e.dtoToFixExtension());
		extensionLogService.createExtLog(extension.makeLog(extension, httpIpInterceptor.getIp(), CREATE));
	}
}
