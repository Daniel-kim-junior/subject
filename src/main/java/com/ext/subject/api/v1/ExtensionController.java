package com.ext.subject.api.v1;

import static com.ext.subject.dto.ExtensionDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ext.subject.service.ExtensionService;
import com.ext.subject.util.common.ApiResponse;

@RestController
@Validated
@RequestMapping(value = "/api-v1")
public class ExtensionController {
	private final ExtensionService extensionService;

	protected ExtensionController(final ExtensionService extensionService) {
		this.extensionService = extensionService;
	}

	@GetMapping("/ext-fixed-list")
	public ApiResponse<List<GetFixedResDto>> getFixedList() {
		List<GetFixedResDto> getFixedResDtos = extensionService.readFixedExtensions();
		return ApiResponse.createSuccess(getFixedResDtos);
	}

	@GetMapping("/ext-custom-list")
	public ApiResponse<List<GetCustomResDto>> getCustomList() {
		List<GetCustomResDto> getCustomResDtos = extensionService.readCustomExtensions();
		return ApiResponse.createSuccess(getCustomResDtos);
	}

	@PatchMapping("/ext-fixed")
	public ApiResponse patchFixedExt(final PatchFixedReqDto dto) {
		extensionService.updateFixExtension(dto);
		return ApiResponse.createSuccessNoContent();
	}

	@PostMapping("/ext-custom")
	public ApiResponse postCustomExt(@Valid final PostCustomReqDto dto) {
		extensionService.createCustomExtension(dto);
		return ApiResponse.createSuccessNoContent();
	}

	@DeleteMapping("/ext-custom")
	public ApiResponse deleteCustomExt(final DeleteCustomReqDto dto) {
		extensionService.deleteCustomExtension(dto);
		return ApiResponse.createSuccessNoContent();
	}

}
