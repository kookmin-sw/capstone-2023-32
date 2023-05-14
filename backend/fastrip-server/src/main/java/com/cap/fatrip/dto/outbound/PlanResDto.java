package com.cap.fatrip.dto.outbound;

import com.cap.fatrip.dto.PlanDto;
import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.entity.PlanTagEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanResDto {
	private long id;
	private String title;
	private int like;
	private String userId;
	private List<String> tags;

	public static PlanResDto of(PlanDto dto) {
		PlanResDto resDto = new PlanResDto();
		resDto.id = dto.getId();
		resDto.title = dto.getTitle();
		resDto.like = dto.getLike();
		resDto.tags = dto.getTags();
		return resDto;
	}

	public static PlanResDto of(PlanEntity plan) {
		PlanResDto resDto = new PlanResDto();
		resDto.id = plan.getId();
		resDto.title = plan.getTitle();
		resDto.like = plan.getLike();
		resDto.userId = plan.getUserId();
		resDto.tags = new ArrayList<>();
		if (plan.getPlanTagEntities().size() != 0) {
			for (PlanTagEntity planTagEntity : plan.getPlanTagEntities()) {
				resDto.tags.add(planTagEntity.getTag().getName());
			}
		}
		return resDto;
	}
}
