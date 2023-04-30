package com.cap.fatrip.entity;
import com.cap.fatrip.dto.PPlanDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "pplan")
public class PPlanEntity implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name="p_id") //계획번호
    private PlanEntity plan;

    @ManyToOne
    @JoinColumn(name="p_no") //장소번호
    private PlaceEntity place;

    @Column(name="p_time") //게획 날짜
    private Date p_time;
    @Column(name="p_comment") //계획 설명
    private String p_comment;
    @Column(name="p_seq") // 계획 순서
    private int p_seq;

    public PPlanEntity toPPlanEntity(PPlanDto pplanDto){
        PPlanEntity pplanEntity = new PPlanEntity();
        pplanEntity.plan = pplanDto.getPlan();
        pplanEntity.place = pplanDto.getPlace();
        pplanEntity.p_time = pplanDto.getP_time();
        pplanEntity.p_comment = pplanDto.getP_comment();
        pplanEntity.p_seq = pplanDto.getP_seq();
        return pplanEntity;
    }
}
