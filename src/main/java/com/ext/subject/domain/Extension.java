package com.ext.subject.domain;

import static com.ext.subject.dto.ExtensionDto.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;
import com.ext.subject.util.common.ExtReqType;
import com.ext.subject.util.common.ExtensionCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(
	name = "extension",
	uniqueConstraints = {
		@UniqueConstraint(
			name="ext_name",
			columnNames = "ext_name"
		)
	}
)
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Extension {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;


	@Column(name = "ext_name")
	@NonNull
	private String name;


	@Column(name = "ext_isActivate")
	private boolean isActivate;

	@Column(name = "ext_category")
	@NonNull
	@Enumerated(EnumType.STRING)
	private ExtensionCategory category;


	public Extension changeActiveStatus() {
		isActivate = isActivate == true ? false : true;
		return this;
	}

	public ExtReqType getFixedLogType() {
		return isActivate ? ExtReqType.ACTIVATE : ExtReqType.DEACTIVATE;
	}

	public ExtensionLog makeLog(Extension extension, String ip, ExtReqType extReqType) {
		return ExtensionLog.builder()
			.extension(extension)
			.extReqType(extReqType)
			.changeIp(ip)
			.build();
	}

	public GetFixedResDto fixExtToDto() {
		return GetFixedResDto.builder()
			.extName(this.name)
			.isActivate(this.isActivate)
			.build();
	}

	public GetCustomResDto customExtToDto() {
		return GetCustomResDto.builder()
			.extName(this.name)
			.build();
	}

	@Builder
	public Extension(final String name, final ExtensionCategory category, final boolean isActivate) {
		this.name = name;
		this.category = category;
		this.isActivate = isActivate;
	}

}
