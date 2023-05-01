package com.cap.fatrip.dto.inbound;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class PlanReqDto {
	private int expense;
	private int days;
	private Purpose[] purpose;

	public enum Purpose {
		activity, tourism, rest
	}
}
