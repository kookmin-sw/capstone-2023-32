package com.cap.fatrip.dto.outbound;

import com.cap.fatrip.dto.inbound.PlanReqDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlanResDto {
	private long planId;
	private int like;
	private String userId;
	private PlanReqDto.Tag[] tag;
}
