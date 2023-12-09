package com.ext.subject.service;

import org.springframework.stereotype.Service;

import com.ext.subject.domain.ExtensionLog;
import com.ext.subject.repository.ExtensionLogRepository;

@Service
public class ExtensionLogService {

	private final ExtensionLogRepository extensionLogRepository;

	protected ExtensionLogService(final ExtensionLogRepository extensionLogRepository) {
		this.extensionLogRepository = extensionLogRepository;
	}

	public void createExtLog(final ExtensionLog makeLog) {

	}
}
