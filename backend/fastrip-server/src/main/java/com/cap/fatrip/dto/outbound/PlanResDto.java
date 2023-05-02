package com.cap.fatrip.dto.outbound;

import com.cap.fatrip.dto.inbound.PlanReqDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlanResDto {
	private long planId;
	private double star;
	private String userId;
	private PlanReqDto.Purpose[] purpose;
}
