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
    private long p_id;
    private String userId;
    private UserEntity user;
    private int like;
    private Date p_c_date; //테스트를 위한 데이터
    private boolean open;

    public static PlanDto of(PlanEntity planEntity){
        PlanDto plan = new PlanDto();
        plan.p_id = planEntity.getId();
        plan.user = planEntity.getUser();
        plan.userId = planEntity.getUserId();
		plan.like = planEntity.getLike();
		plan.open = planEntity.isOpen();
        plan.createdAt = planEntity.getCreatedAt();
		plan.updatedAt = planEntity.getUpdatedAt();
        return plan;
    }
}
