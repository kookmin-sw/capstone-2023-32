package com.cap.fatrip.dto.outbound;

import com.cap.fatrip.dto.PlanDto;
import com.cap.fatrip.dto.TimeDto;
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
public class PlanResDto extends TimeDto {
	private String id;
	private String title;
	private int like;
	private String userId;
	private String comment;
	private String image;
	private String category;
	private List<String> tags;

	public static PlanResDto of(PlanDto dto) {
		PlanResDto resDto = new PlanResDto();
		resDto.id = dto.getId();
		resDto.title = dto.getTitle();
		resDto.like = dto.getLike();
		resDto.tags = dto.getTags();    //tag부분을 저장하는 코드가 정리가 안되서 일단 주석처리 했습니다.
		return resDto;
	}

	public static PlanResDto of(PlanEntity plan) {
		PlanResDto resDto = new PlanResDto();
		resDto.id = plan.getId();
		resDto.title = plan.getTitle();
		resDto.like = plan.getLikes();
		resDto.image = plan.getImage();
		resDto.userId = plan.getUser().getId();
		resDto.comment = plan.getComment();
		resDto.category = plan.getCategory();
		resDto.createdAt = plan.getCreatedAt();
		resDto.updatedAt = plan.getUpdatedAt();
		resDto.tags = new ArrayList<>();
		if (plan.getPlanTagEntities().size() != 0) {
			for (PlanTagEntity planTagEntity : plan.getPlanTagEntities()) {
				resDto.tags.add(planTagEntity.getTag().getName());
			}
		}
		return resDto;
	}
}
