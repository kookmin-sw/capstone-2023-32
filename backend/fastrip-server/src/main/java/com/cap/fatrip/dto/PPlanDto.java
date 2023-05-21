package com.cap.fatrip.dto;

import com.cap.fatrip.entity.PPlanEntity;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PPlanDto {
    private int day;
    private int p_seq;

    private String p_name; //place와 병합
    private String p_post;
    private String p_locate;
    private String p_country;


    public static PPlanDto of(PPlanEntity pplanEntity){
        PPlanDto plan = new PPlanDto();
        plan.day = pplanEntity.getDay();
        plan.p_seq = pplanEntity.getSeq();
        plan.p_name = pplanEntity.getP_name();
        plan.p_post = pplanEntity.getP_post();
        plan.p_locate = pplanEntity.getP_locate();
        plan.p_country = pplanEntity.getP_country();
        return plan;
    }
}
