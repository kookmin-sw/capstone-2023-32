package com.cap.fatrip.dto.inbound;

import lombok.Getter;

@Getter
public class PlanReqDto {
	private int expense;
	private int days;
	private Tag[] tag;

	public enum Tag {
		activity, tourism, rest
	}
}
