package com.ext.subject.dto;

import static com.ext.subject.util.common.ExtensionCategory.*;
import static lombok.AccessLevel.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.cglib.core.Local;

import com.ext.subject.domain.Extension;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ExtensionDto {

	@Getter
	@NoArgsConstructor(access = PROTECTED)
	public static class PatchFixedReqDto {

		@NotBlank(message = "요청이 잘못 되었습니다")
		private String extName;

		@NotNull(message = "요청이 잘못 되었습니다")
		private Boolean isActivate;

		public PatchFixedReqDto(final String extName, final Boolean isActivate) {
			this.extName = extName;
			this.isActivate = isActivate;
		}

	}

	@NoArgsConstructor(access = PROTECTED)
	@Getter
	public static class GetFixedResDto {
		@NotNull
		private String extName;

		@NotNull
		private Boolean isActivate;

		@NotNull
		private LocalDateTime expiredDate;

		@Builder
		public GetFixedResDto(final String extName, final Boolean isActivate) {
			this.extName = extName;
			this.isActivate = isActivate;
			this.expiredDate = LocalDateTime.now().plusDays(10);
		}
	}


	@NoArgsConstructor(access = PROTECTED)
	@Getter
	public static class GetCustomResDto {
		@NotNull
		private String extName;

		@NotNull
		private LocalDateTime expiredDate;

		@Builder
		public GetCustomResDto(String extName) {
			this.extName = extName;
			this.expiredDate = LocalDateTime.now().plusDays(10);
		}
	}

	@NoArgsConstructor(access = PROTECTED)
	@Getter
	public static class DeleteCustomReqDto {

		@NotBlank(message = "요청이 잘못 되었습니다")
		private String extName;

		public DeleteCustomReqDto(final String extName) {
			this.extName = extName;
		}
	}

	@NoArgsConstructor(access = PROTECTED)
	@Getter
	public static class PostCustomReqDto {
		@NotNull(message = "요청이 잘못 되었습니다")
		@Size(min = 1, max = 20, message = "1글자에서 20글자 사이로 입력해주세요.")
		@Pattern(regexp = "^[a-z]*$", message = "공백이 포함될 수 없습니다, 영어 소문자만 지원합니다")
		private String extName;

		public PostCustomReqDto(final String extName) {
			this.extName = extName.trim();
		}
		public Extension customDtoToExtension() {
			return Extension.builder()
				.name(this.extName)
				.category(CUSTOM)
				.build();
		}
	}

	@Getter
	@NoArgsConstructor(access = PROTECTED)
	public static class PostFixedReqDto {

		@NotNull(message = "요청이 잘못 되었습니다")
		@Size(min = 1, max = 20, message = "1글자에서 20글자 사이로 영문 입력해주세요.")
		@Pattern(regexp = "^[a-z]*$", message = "공백이 포함될 수 없습니다, 영어 소문자만 지원합니다")
		private String extName;

		private Boolean isActivate;

		public PostFixedReqDto(String extName) {
			this.extName = extName;
			this.isActivate = false;
		}

		public Extension dtoToFixExtension() {
			return Extension.builder()
				.name(this.extName)
				.category(FIXED)
				.isActivate(false)
				.build();
		}
	}
}
