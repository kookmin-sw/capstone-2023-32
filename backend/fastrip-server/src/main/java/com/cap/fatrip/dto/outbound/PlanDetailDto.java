package com.cap.fatrip.dto.outbound;

import com.cap.fatrip.dto.PPlanDto;
import com.cap.fatrip.dto.PlanDto;
import lombok.Data;

import java.util.List;

@Data
public class PlanDetailDto {
	private PlanDto plan;
	private List<PPlanDto> pplan;
}
