package com.cap.fatrip.entity;

import com.cap.fatrip.dto.PPlanDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "pplan")
public class PPlanEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pp_id")
    private Long pp_id;    //없으면 에러가 발생

    @ManyToOne
    @JoinColumn(name="p_id") //계획번호
    private PlanEntity plan;

    @Column
    private int day;
    @Column // 계획 순서
    private int seq;

    @Column   //place와 병합
    private String p_name;
    @Column
    private String p_post;
    @Column
    private String p_locate;
    @Column
    private String p_country;


    public static PPlanEntity of(PPlanDto pplanDto){
        PPlanEntity pplanEntity = new PPlanEntity();
        pplanEntity.day = pplanDto.getDay();
        pplanEntity.seq = pplanDto.getP_seq();
        pplanEntity.p_name = pplanDto.getP_name();
        pplanEntity.p_post = pplanDto.getP_post();
        pplanEntity.p_locate = pplanDto.getP_locate();
        pplanEntity.p_country = pplanDto.getP_country();
        return pplanEntity;
    }
}
