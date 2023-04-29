package com.cap.fatrip.dto;
import com.cap.fatrip.entity.PPlanEntity;
import com.cap.fatrip.entity.PlaceEntity;
import com.cap.fatrip.entity.PlanEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PPlanDto {
    private PlanEntity plan;
    private PlaceEntity place;
    private Date p_time;
    private String p_comment;
    private int p_seq;

    public static PPlanDto of(PPlanEntity pplanEntity){
        PPlanDto plan = new PPlanDto();
        plan.plan = pplanEntity.getPlan();
        plan.place = pplanEntity.getPlace();
        plan.p_time = pplanEntity.getP_time();
        plan.p_comment = pplanEntity.getP_comment();
        plan.p_seq = pplanEntity.getP_seq();
        return plan;
    }
}
