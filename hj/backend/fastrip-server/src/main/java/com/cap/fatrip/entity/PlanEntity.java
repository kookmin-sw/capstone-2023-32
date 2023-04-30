package com.cap.fatrip.entity;


import com.cap.fatrip.dto.PlanDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "plan")
public class PlanEntity {
    @Id
    @Column(name="p_id") //계획 id
    private long p_id;

    @ManyToOne
    @JoinColumn(name = "id",nullable = false)   //fk
    private UserEntity user;
    @Column(name="p_c_date") //계획생성일
    private Date p_c_date;
    @Column(name="cost")
    private int cost;

    public static PlanEntity toPlanEntity(PlanDto planDto){
        PlanEntity planEntity = new PlanEntity();
        planEntity.p_id = planDto.getP_id();
        planEntity.user = planDto.getUser();
        planEntity.p_c_date = planDto.getP_c_date();
        planEntity.cost = planDto.getCost();
        return planEntity;
    }
}
