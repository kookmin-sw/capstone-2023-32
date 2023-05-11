package com.cap.fatrip.dto;

import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private String[] tags;
    private Date createDate; //테스트를 위한 데이터
    private boolean open;

    public static PlanDto of(PlanEntity planEntity){
        PlanDto plan = new PlanDto();
        plan.id = planEntity.getId();
        plan.subject = planEntity.getSubject();
        plan.user = planEntity.getUser();
        plan.userId = planEntity.getUserId();
		plan.like = planEntity.getLike();
//        plan.tag = planEntity.getTags();
		plan.open = planEntity.isOpen();
        plan.createdAt = planEntity.getCreatedAt();
		plan.updatedAt = planEntity.getUpdatedAt();
        return plan;
    }
}
