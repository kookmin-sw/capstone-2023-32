package com.cap.fatrip.dto;

import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanDto extends TimeDto {
    private long id;
    private String subject;
    private String userId;
    private UserEntity user;
    private int like;
    private List<String> tags;
    private Date createDate; //테스트를 위한 데이터
    private boolean open;

    private String title;

    public static PlanDto of(PlanEntity planEntity){
        PlanDto plan = new PlanDto();
        plan.id = planEntity.getId();
        plan.subject = planEntity.getSubject();
        plan.user = planEntity.getUser();
        plan.userId = planEntity.getUserId();
		plan.like = planEntity.getLike();
        plan.tags = planEntity.getTags();
		plan.open = planEntity.isOpen();
        plan.createdAt = planEntity.getCreatedAt();
		plan.updatedAt = planEntity.getUpdatedAt();
        plan.title = planEntity.getTitle();
        return plan;
    }
}
