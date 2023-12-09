package com.ext.subject.domain;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(
	name = "extension_log"
)
public class ExtensionLog {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn
	private Extension extension;


	private String changeIp;

	private String changeMac;

	@CreatedDate
	private LocalDateTime createdDt;

	@Builder
	public ExtensionLog(final Extension extension, final String changeIp, final String changeMac) {
		this.extension = extension;
		this.changeIp = changeIp;
		this.changeMac = changeMac;
	}
}
