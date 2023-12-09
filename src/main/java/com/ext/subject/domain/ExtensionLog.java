package com.ext.subject.domain;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.ext.subject.util.common.ExtReqType;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(
	name = "extension_log"
)
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = PROTECTED)
public class ExtensionLog {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn
	private Extension extension;


	private String changeIp;

	@Enumerated(EnumType.STRING)
	private ExtReqType extReqType;

	@CreatedDate
	private LocalDateTime createdDt;

	@Builder
	public ExtensionLog(final Extension extension, final String changeIp, final ExtReqType extReqType) {
		this.extension = extension;
		this.changeIp = changeIp;
		this.extReqType = extReqType;
	}
}
