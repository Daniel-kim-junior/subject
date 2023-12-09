package com.ext.subject.domain;

import static jakarta.persistence.GenerationType.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

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
public class Extension {

	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "ext_id")
	private Long id;


	@Column(name = "ext_name")
	private String name;


}
