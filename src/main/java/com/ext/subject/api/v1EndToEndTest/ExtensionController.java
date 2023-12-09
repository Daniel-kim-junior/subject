package com.ext.subject.api.v1EndToEndTest;

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

import com.ext.subject.service.ExtensionService;
import com.ext.subject.util.common.ApiResponse;

@RestController
@RequestMapping(value = "/api-v1")
public class ExtensionController {
	private final ExtensionService extensionService;

	public ExtensionController(final ExtensionService extensionService) {
		this.extensionService = extensionService;
	}

	@PostMapping("/ext-fixed-list")
	public ApiResponse postInitFixedList(@RequestBody @Validated List<PostFixedReqDto> list) {
		extensionService.createInitFixedList(list);
		return ApiResponse.createSuccessNoContent();
	}


	@GetMapping("/excl/ext-fixed-list")
	public ApiResponse<List<GetFixedResDto>> getFixedList() {
		List<GetFixedResDto> getFixedResDtos = extensionService.readFixedExtensions();
		return ApiResponse.createSuccess(getFixedResDtos);
	}

	@GetMapping("/excl/ext-custom-list")
	public ApiResponse<List<GetCustomResDto>> getCustomList() {
		List<GetCustomResDto> getCustomResDtos = extensionService.readCustomExtensions();
		return ApiResponse.createSuccess(getCustomResDtos);
	}

	@PatchMapping("/ext-fixed")
	public ApiResponse patchFixedExt(@RequestBody @Validated PatchFixedReqDto dto) {
		extensionService.updateFixExtension(dto);
		return ApiResponse.createSuccessNoContent();
	}

	@PostMapping("/ext-custom")
	public ApiResponse postCustomExt(@RequestBody @Validated PostCustomReqDto dto) {
		extensionService.createCustomExtension(dto);
		return ApiResponse.createSuccessNoContent();
	}

	@DeleteMapping("/ext-custom")
	public ApiResponse deleteCustomExt(@RequestBody final DeleteCustomReqDto dto) {
		extensionService.deleteCustomExtension(dto);
		return ApiResponse.createSuccessNoContent();
	}

}
