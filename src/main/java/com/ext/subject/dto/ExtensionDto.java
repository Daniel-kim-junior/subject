package com.ext.subject.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

		public PatchFixedReqDto(String extName, Boolean isActivate) {
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
		public GetFixedResDto(String extName, Boolean isActivate) {
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
		public FixedListCacheData(List<GetFixedResDto> data, LocalDateTime expirationDate) {
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
		public CustomListCacheData(List<GetCustomResDto> data, LocalDateTime expirationDate) {
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
		public GetCustomResDto(String extName) {
			this.extName = extName;
		}
	}

	@NoArgsConstructor
	@Getter
	public static class DeleteCustomReqDto {
		private String extName;

		public DeleteCustomReqDto(String extName) {
			this.extName = extName;
		}
	}

	@NoArgsConstructor
	@Getter
	public static class PostCustomReqDto {
		private String extName;

		public PostCustomReqDto(String extName) {
			this.extName = extName;
		}
		public Extension customDtoToExtension(PostCustomReqDto dto) {
			return Extension.builder()
				.name(dto.extName)
				.category(ExtensionCategory.CUSTOM)
				.build();
		}
	}
}
