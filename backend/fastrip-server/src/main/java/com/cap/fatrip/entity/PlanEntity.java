package com.cap.fatrip.entity;


import com.cap.fatrip.dto.PlanDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Column(name = "p_subject")
    private String subject;
    @Column(name = "u_id")
    // test
    private String userId;
    @OneToMany(mappedBy = "plan", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<PlanTagEntity> tags = new ArrayList<>();
    @Column(name= "p_like")
    private int like;
    @Column(name = "p_open")
    private boolean open;

    public static PlanEntity toPlanEntity(PlanDto planDto){
        PlanEntity planEntity = new PlanEntity();
        planEntity.id = planDto.getId();
        planEntity.user = planDto.getUser();
        return planEntity;
    }
}
