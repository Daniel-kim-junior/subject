package com.ext.subject.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ext.subject.domain.Extension;
import com.ext.subject.util.common.ExtensionCategory;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ExtensionDto {

	@Getter
	@NoArgsConstructor
	public static class PatchFixedReqDto {
		private String extName;

		private Boolean isActivate;

		public PatchFixedReqDto(final String extName, final Boolean isActivate) {
			this.extName = extName;
			this.isActivate = isActivate;
		}

	}

	@NoArgsConstructor
	@Getter
	public static class GetFixedResDto {
		private String extName;

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
		private List<GetFixedResDto> data;

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



	@NoArgsConstructor
	@Getter
	public static class PatchFixedResDto {


	}

	@NoArgsConstructor
	@Getter
	public static class CustomListCacheData implements Serializable {
		private List<GetCustomResDto> data;

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

	@NoArgsConstructor
	@Getter
	public static class GetCustomResDto {
		private String extName;

		@Builder
		public GetCustomResDto(final String extName) {
			this.extName = extName;
		}
	}

	@NoArgsConstructor
	@Getter
	public static class DeleteCustomReqDto {
		private String extName;

		public DeleteCustomReqDto(final String extName) {
			this.extName = extName;
		}
	}

	@NoArgsConstructor
	@Getter
	public static class PostCustomReqDto {
		@NotBlank(message = "공백이 포함되어 있습니다")
		@Size(min = 1, max = 20, message = "1글자에서 20글자 사이로 입력해주세요.")
		@Pattern(regexp = "^[a-zA-Z]*$", message = "영어만 입력 가능합니다.")
		private String extName;

		public PostCustomReqDto(final String extName) {
			this.extName = extName;
		}
		public Extension customDtoToExtension(final PostCustomReqDto dto) {
			return Extension.builder()
				.name(dto.extName)
				.category(ExtensionCategory.CUSTOM)
				.build();
		}
	}
}
