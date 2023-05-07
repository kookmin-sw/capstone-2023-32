package com.cap.fatrip.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {

	@Column(name = "created_date")
	@CreatedDate
	private LocalDateTime createdAt;

	@Column(name = "modified_date")
	@LastModifiedDate
	private LocalDateTime updatedAt;

}