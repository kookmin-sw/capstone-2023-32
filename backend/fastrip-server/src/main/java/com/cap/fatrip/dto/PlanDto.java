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
public class PlanDto {
    private long p_id;
    private UserEntity user;
    private Date p_c_date;
    private int cost;

    public static PlanDto of(PlanEntity planEntity){
        PlanDto plan = new PlanDto();
        plan.p_id = planEntity.getP_id();
        plan.user = planEntity.getUser();
        plan.cost = planEntity.getCost();
        plan.p_c_date = planEntity.getP_c_date();
        return plan;
    }
}
