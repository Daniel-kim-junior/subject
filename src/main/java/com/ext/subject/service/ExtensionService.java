package com.ext.subject.service;

import static com.ext.subject.dto.ExtensionDto.*;
import static com.ext.subject.util.common.ExtReqType.*;
import static com.ext.subject.util.common.ExtensionCategory.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ext.subject.domain.Extension;
import com.ext.subject.repository.ExtensionRepository;
import com.ext.subject.util.common.Exceed200EaException;
import com.ext.subject.util.common.ExtReqType;
import com.ext.subject.util.common.ExtensionNotFoundException;
import com.ext.subject.util.common.HttpIpInterceptor;

@Service
public class ExtensionService {

	private final ExtensionRepository extensionRepository;

	private final ExtensionLogService extensionLogService;

	private final HttpIpInterceptor httpIpInterceptor;

	private static final String DEL_NOT_FOUND = "삭제 하려는 확장자가 존재하지 않습니다";

	private static final String CHA_NOT_FOUND = "변경 하려는 확장자가 존재하지 않습니다";

	protected ExtensionService(final ExtensionRepository extensionRepository,
		final HttpIpInterceptor httpIpInterceptor,
		final ExtensionLogService extensionLogService) {
		this.extensionRepository = extensionRepository;
		this.httpIpInterceptor = httpIpInterceptor;
		this.extensionLogService = extensionLogService;
	}

	@Transactional
	public List<GetCustomResDto> createCustomExtension(final PostCustomReqDto dto) {
		/**
		 * validation
		 * 1. 공백을 포함하거나 영어가 아닌 문자가 포함되는 확장자 이름일 때
		 * 2. 20글자가 넘는 확장자
		 * 3. 200개가 넘을 때
		 * 4. 중복되는 확장자일 때(hibernate exception unique key)
		 * 5. 고정 확장자에 속해 있는 확장자 이름일 때
		 */
		validationCustom();
		Extension extension = extensionRepository.save(dto.customDtoToExtension());
		createLog(extension, CREATE);
		return readCustomExtensions();
	}

	private void validationCustom() {
		if (extensionRepository.findByCategory(CUSTOM).size() >= 200) {
			throw new Exceed200EaException("최대 200개의 확장자만 등록할 수 있습니다");
		}
	}

	@Transactional
	public List<GetFixedResDto> updateFixExtension(final PatchFixedReqDto dto) {
		Extension extension = extensionRepository.findByName(dto.getExtName())
			.orElseThrow(() -> new ExtensionNotFoundException(CHA_NOT_FOUND));
		extension.changeActiveStatus();
		createLog(extension, extension.getFixedLogType());
		return readFixedExtensions();
	}

	@Transactional
	public List<GetCustomResDto> deleteCustomExtension(final DeleteCustomReqDto dto) {
		Extension extension = extensionRepository.findByName(dto.getExtName())
			.orElseThrow(() -> new ExtensionNotFoundException(DEL_NOT_FOUND));
		extensionRepository.delete(extension);
		createLog(extension, DELETE);
		return readCustomExtensions();
	}

	public List<GetFixedResDto> readFixedExtensions() {
		return extensionRepository.findByCategory(FIXED)
			.stream().map(e -> e.fixExtToDto())
			.collect(Collectors.toList());
	}

	public List<GetCustomResDto> readCustomExtensions() {
		return extensionRepository.findByCategory(CUSTOM)
			.stream().map(e -> e.customExtToDto())
			.collect(Collectors.toList());
	}

	@Transactional
	public List<GetFixedResDto> createInitFixedList(List<PostFixedReqDto> list) {
		list.stream().forEach(this::proceedSave);
		return readFixedExtensions();
	}


	private void createLog(Extension extension, ExtReqType type) {
		extensionLogService.createExtLog(extension.makeLog(extension, httpIpInterceptor.getIp(), type));
	}

	private void proceedSave(PostFixedReqDto e) {
		Extension extension = extensionRepository.save(e.dtoToFixExtension());
		createLog(extension, CREATE);
	}
}
