package com.cap.fatrip.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeDto {
	protected LocalDateTime createdAt;
	protected LocalDateTime updatedAt;
}
