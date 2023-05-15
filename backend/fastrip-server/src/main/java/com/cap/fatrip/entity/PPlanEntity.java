package com.cap.fatrip.entity;

import com.cap.fatrip.dto.PPlanDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "pplan")
public class PPlanEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pp_id", nullable = true)
    private Long pp_id;    //없으면 에러가 발생

    @ManyToOne
    @JoinColumn(name="p_id") //계획번호
    private PlanEntity plan;

    /*@ManyToOne
    @JoinColumn(name="p_no") //장소번호
    private PlaceEntity place;
*/

    @Column(name="day") //게획 날짜
    private Date day;
    @Column(name="p_comment") //계획 설명
    private String p_comment;
    @Column(name="p_seq") // 계획 순서
    private int p_seq;

    @Column(name="p_name")   //place와 병합
    private String p_name;
    @Column(name="p_post")
    private String p_post;
    @Column(name="p_locate")
    private String p_locate;
    @Column(name="p_country")
    private String p_country;


    public static PPlanEntity toPPlanEntity(PPlanDto pplanDto){
        PPlanEntity pplanEntity = new PPlanEntity();
        pplanEntity.plan = pplanDto.getPlan();
        //pplanEntity.place = pplanDto.getPlace();
        pplanEntity.day = pplanDto.getDay();
        pplanEntity.p_comment = pplanDto.getP_comment();
        pplanEntity.p_seq = pplanDto.getP_seq();
        pplanEntity.p_name = pplanDto.getP_name();
        pplanEntity.p_post = pplanDto.getP_post();
        pplanEntity.p_locate = pplanDto.getP_locate();
        pplanEntity.p_country = pplanDto.getP_country();
        return pplanEntity;
    }
}
