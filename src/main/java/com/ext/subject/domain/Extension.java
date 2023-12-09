package com.ext.subject.domain;

import static jakarta.persistence.GenerationType.*;

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
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@Table(
	name = "extension",
	uniqueConstraints = {
		@UniqueConstraint(
			name="ext_name",
			columnNames = "ext_name"
		)
	}
)
public class Extension {

	@Builder
	public Extension(String name, ExtensionCategory category) {
		this.name = name;
		this.category = category;
	}

	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "ext_id")
	private Long id;


	@Column(name = "ext_name")
	@NonNull
	private String name;


	@Column(name = "ext_isActivate")
	private Boolean isActivate;

	@Column(name = "ext_category")
	@NonNull
	@Enumerated(EnumType.STRING)
	private ExtensionCategory category;


	public Extension changeActiveStatus() {
		isActivate = isActivate == true ? false : true;
		return this;
	}

}
