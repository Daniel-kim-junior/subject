package com.ext.subject.api.v1;

import static com.ext.subject.dto.ExtensionDto.*;

import java.util.List;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ext.subject.service.ExtensionCacheService;
import com.ext.subject.util.common.ApiResponse;

@RestController
@RequestMapping(value = "/api-v1")
public class ExtensionController {
	private final ExtensionCacheService extensionService;

	public ExtensionController(ExtensionCacheService extensionService) {
		this.extensionService = extensionService;
	}

	@PostMapping("/ext-fixed-list")
	public ApiResponse postInitFixedList(@RequestBody @Validated List<PostFixedReqDto> list) {
		extensionService.refreshFixedExtensions(list);
		return ApiResponse.createSuccessNoContent();
	}


	@GetMapping("/excl/ext-fixed-list")
	public ApiResponse<List<GetFixedResDto>> getFixedList() {
		List<GetFixedResDto> getFixedResDtos = extensionService.getFixedCacheData();
		return ApiResponse.createSuccess(getFixedResDtos);
	}

	@GetMapping("/excl/ext-custom-list")
	public ApiResponse<List<GetCustomResDto>> getCustomList() {
		List<GetCustomResDto> getCustomResDtos = extensionService.getCustomCacheData();
		return ApiResponse.createSuccess(getCustomResDtos);
	}

	@PatchMapping("/ext-fixed")
	public ApiResponse patchFixedExt(@RequestBody @Validated PatchFixedReqDto dto) {
		extensionService.patchFixedCacheUpdate(dto);
		return ApiResponse.createSuccessNoContent();
	}

	@PostMapping("/ext-custom")
	public ApiResponse postCustomExt(@RequestBody @Validated PostCustomReqDto dto) {
		extensionService.refreshCustomExtensions(dto);
		return ApiResponse.createSuccessNoContent();
	}

	@DeleteMapping("/ext-custom")
	public ApiResponse deleteCustomExt(@RequestBody final DeleteCustomReqDto dto) {
		extensionService.deleteCustomCacheUpdate(dto);
		return ApiResponse.createSuccessNoContent();
	}

}
