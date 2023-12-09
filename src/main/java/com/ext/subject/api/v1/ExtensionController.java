package com.ext.subject.api.v1;

import static com.ext.subject.dto.ExtensionDto.*;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ext.subject.service.ExtensionService;
import com.ext.subject.util.common.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api-v1")
@RequiredArgsConstructor
public class ExtensionController {
	private ExtensionService extensionService;


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
	public ApiResponse patchFixedExt(PatchFixedReqDto dto) {
		extensionService.updateFixExtension(dto);
		return ApiResponse.createSuccessNoContent();
	}

	@PostMapping("/ext-custom")
	public ApiResponse postCustomExt(PostCustomReqDto dto) {
		extensionService.createCustomExtension(dto);
		return ApiResponse.createSuccessNoContent();
	}

	@DeleteMapping("/ext-custom")
	public ApiResponse deleteCustomExt(DeleteCustomReqDto dto) {
		extensionService.deleteCustomExtension(dto);
		return ApiResponse.createSuccessNoContent();
	}

}
