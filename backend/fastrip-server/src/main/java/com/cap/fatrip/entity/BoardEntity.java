package com.cap.fatrip.entity;

import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long b_key;

    @ManyToOne
    @JoinColumn(name = "u_key")   //fk
    private UserEntity user;

    @Column
    private String b_title;

    @Column
    private String b_date;

    @Column
    private String b_mod_date;

    @Column
    private boolean b_alarm_yn;

    @Column
    private boolean b_mod_yn;

    @Column
    private String b_content;

    @Column
    private int b_report_cnt;

    @Column
    private int b_like_cnt;

    @Column
    private boolean b_noti_yn;

    @Column
    private String b_category;

    @Column
    private boolean b_blind;

    @Column
    private boolean b_anonymous;
}
