package com.cap.fatrip.dto.outbound;

import com.cap.fatrip.dto.PlanDto;
import com.cap.fatrip.entity.PlanEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlanResDto {
	private long id;
	private String subject;
	private int like;
	private String userId;
	private String[] tag;

	public static PlanResDto of(PlanDto dto) {
		PlanResDto resDto = new PlanResDto();
		resDto.id = dto.getId();
		resDto.subject = dto.getSubject();
		resDto.like = dto.getLike();
		resDto.tag = dto.getTags();
		return resDto;
	}

	public static PlanResDto of(PlanEntity entity) {
		PlanResDto resDto = new PlanResDto();
		resDto.id = entity.getId();
		resDto.subject = entity.getSubject();
		resDto.like = entity.getLike();
//		resDto.tag = entity.getTags();
		return resDto;
	}
}
