package com.ext.subject.dto;

import static com.ext.subject.util.common.ExtensionCategory.*;
import static lombok.AccessLevel.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

		@Builder
		public GetFixedResDto(final String extName, final Boolean isActivate) {
			this.extName = extName;
			this.isActivate = isActivate;
		}
	}

	@NoArgsConstructor
	@Getter
	public static class FixedListCacheData implements Serializable {

		@NotNull
		private List<GetFixedResDto> data;

		@NotNull
		private LocalDateTime expirationDate;

		@Builder
		public FixedListCacheData(final List<GetFixedResDto> data, final LocalDateTime expirationDate) {
			this.data = data;
			this.expirationDate = expirationDate;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			final FixedListCacheData that = (FixedListCacheData) o;
			return Objects.equals(data, that.data) && Objects.equals(expirationDate, that.expirationDate);
		}

		@Override
		public int hashCode() {
			return Objects.hash(data, expirationDate);
		}
	}



	@NoArgsConstructor(access = PROTECTED)
	@Getter
	public static class PatchFixedResDto {


	}

	@NoArgsConstructor
	@Getter
	public static class CustomListCacheData implements Serializable {
		@NotNull
		private List<GetCustomResDto> data;

		@NotNull
		private LocalDateTime expirationDate;

		@Builder
		public CustomListCacheData(final List<GetCustomResDto> data, final LocalDateTime expirationDate) {
			this.data = data;
			this.expirationDate = expirationDate;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			final CustomListCacheData that = (CustomListCacheData)o;
			return Objects.equals(data, that.data) && Objects.equals(expirationDate, that.expirationDate);
		}

		@Override
		public int hashCode() {
			return Objects.hash(data, expirationDate);
		}
	}

	@NoArgsConstructor(access = PROTECTED)
	@Getter
	public static class GetCustomResDto {
		@NotNull
		private String extName;

		@Builder
		public GetCustomResDto(final String extName) {
			this.extName = extName;
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
		@NotBlank(message = "요청이 잘못 되었거나 공백이 포함되어 있습니다")
		@Size(min = 1, max = 20, message = "1글자에서 20글자 사이로 입력해주세요.")
		@Pattern(regexp = "^[a-zA-Z]*$", message = "영어만 입력 가능합니다.")
		private String extName;

		public PostCustomReqDto(final String extName) {
			this.extName = extName;
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

		@NotBlank(message = "요청이 잘못 되었거나 공백이 포함되어 있습니다")
		@Size(min = 1, max = 20, message = "1글자에서 20글자 사이로 입력해주세요.")
		@Pattern(regexp = "^[a-zA-Z]*$", message = "영어만 입력 가능합니다.")
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
				.build();
		}
	}
}
