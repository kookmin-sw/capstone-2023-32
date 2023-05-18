package com.cap.fatrip.entity;


import com.cap.fatrip.dto.PlanDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    @JoinColumn(name = "user_id")   //fk
    private UserEntity user;
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<PlanTagEntity> planTagEntities = new ArrayList<>();
    @Column(name= "p_like")
    private int like;
    @Column(name = "p_open")
    private boolean open;
    @Column(name = "title")
    private String title;

    @Column(name = "comment")
    private String comment;
    @Column(name = "image")
    private String image;



    public static PlanEntity toPlanEntity(PlanDto planDto){
        PlanEntity planEntity = new PlanEntity();
        planEntity.id = planDto.getId();
        planEntity.user = planDto.getUser();

        planEntity.comment = planDto.getComment();
        planEntity.title = planDto.getTitle();
        return planEntity;
    }
}
