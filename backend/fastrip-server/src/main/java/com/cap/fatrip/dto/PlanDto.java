package com.cap.fatrip.dto;

import com.cap.fatrip.entity.PlanEntity;
import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanDto extends TimeDto {
    private long id;
    private String userId;
    private int like;
//    private boolean open;
    private List<String> tags;

    private String comment;
    private String title;
    private String image;

    public static PlanDto of(PlanEntity planEntity){
        PlanDto plan = new PlanDto();
        plan.id = planEntity.getId();
        plan.userId = planEntity.getUser().getId();
		plan.like = planEntity.getLikes();
//		plan.open = planEntity.isOpen();
        plan.createdAt = planEntity.getCreatedAt();
		plan.updatedAt = planEntity.getUpdatedAt();
        plan.comment = planEntity.getComment();
        plan.title = planEntity.getTitle();
        plan.image = planEntity.getImage();
        return plan;
    }
}
