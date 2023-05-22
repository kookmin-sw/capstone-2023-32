package com.cap.fatrip.dto.inbound;

import lombok.Getter;

import java.util.List;

@Getter
public class PlanReqDto {
	private String title;
	private List<String> tags;
}
