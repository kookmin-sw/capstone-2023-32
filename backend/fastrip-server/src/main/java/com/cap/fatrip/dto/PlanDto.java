package com.cap.fatrip.dto;

import com.cap.fatrip.entity.PlanEntity;
import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanDto extends TimeDto {
    private String id;
    private String userId;
    private int like;
    private List<String> tags;
    private String comment;
    private String title;
    private String image;
    private String category;

    public static PlanDto of(PlanEntity planEntity){
        PlanDto plan = new PlanDto();
        plan.id = planEntity.getId();
        plan.userId = planEntity.getUser().getId();
		plan.like = planEntity.getLikes();
        plan.createdAt = planEntity.getCreatedAt();
		plan.updatedAt = planEntity.getUpdatedAt();
        plan.comment = planEntity.getComment();
        plan.title = planEntity.getTitle();
        plan.image = planEntity.getImage();
        plan.category = planEntity.getCategory();
        return plan;
    }
}
