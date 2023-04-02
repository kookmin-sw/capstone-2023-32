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
	private String createdDate;

	@Column(name = "modified_date")
	@LastModifiedDate
	private String modifiedDate;

	// 해당 엔티티를 저장하기 이전에 실행
	@PrePersist
	public void onPrePersist() {
		this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
		this.modifiedDate = this.createdDate;
	}

	// 해당 엔티티를 업데이트 하기 이전에 실행
	@PreUpdate
	public void onPreUpdate() {
		this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
	}
}