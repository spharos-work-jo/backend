package com.workjo.pointapp.common.domain;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseRegDateTime {

	@Column(name = "reg_date", updatable = false)
	@CreatedDate
	private LocalDateTime regDate;

}