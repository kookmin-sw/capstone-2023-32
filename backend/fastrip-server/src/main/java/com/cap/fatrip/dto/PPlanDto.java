package com.cap.fatrip.dto;

import com.cap.fatrip.entity.PPlanEntity;
import com.cap.fatrip.entity.PlaceEntity;
import com.cap.fatrip.entity.PlanEntity;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PPlanDto {

    private PlanEntity plan;
    // private PlaceEntity place;
    private Date day;
    private String p_comment;
    private int p_seq;

    private String p_name; //place와 병합
    private String p_post;
    private String p_locate;
    private String p_country;


    public static PPlanDto of(PPlanEntity pplanEntity){
        PPlanDto plan = new PPlanDto();
        plan.plan = pplanEntity.getPlan();
        //plan.place = pplanEntity.getPlace();
        plan.day = pplanEntity.getDay();
        plan.p_comment = pplanEntity.getP_comment();
        plan.p_seq = pplanEntity.getP_seq();
        plan.p_name = pplanEntity.getP_name();
        plan.p_post = pplanEntity.getP_post();
        plan.p_locate = plan.getP_locate();
        plan.p_country = plan.getP_country();
        return plan;
    }
}
