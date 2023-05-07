package com.cap.fatrip.entity;


import com.cap.fatrip.dto.PlanDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plan")
public class PlanEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="p_id") //계획 id
    private long id;

    @ManyToOne
//    @JoinColumn(name = "id",nullable = false)   //fk
    @JoinColumn(name = "id", insertable = false, updatable = false)   //fk
    private UserEntity user;
    @Column(name = "u_id")
    // test
    private String userId;
    @OneToMany(mappedBy = "tag")
    private List<PurposeEntity> purposes = new ArrayList<>();
    @Column(name = "p_star_total")
    private double starTotal;
    @Column(name= "p_star_cnt")
    private int starCnt;
    @Column(name = "p_open")
    private boolean open;
    @Column(name="cost")
    private int cost;

    public static PlanEntity toPlanEntity(PlanDto planDto){
        PlanEntity planEntity = new PlanEntity();
        planEntity.id = planDto.getP_id();
        planEntity.user = planDto.getUser();
        planEntity.cost = planDto.getCost();
        return planEntity;
    }
}
