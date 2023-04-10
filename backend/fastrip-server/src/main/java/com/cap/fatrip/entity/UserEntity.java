package com.cap.fatrip.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long u_key;

    @Column(length = 20)
    private String u_id;

    @Column(length = 20)
    private String u_pw;

    @Column(length = 20)
    private String u_name;

    @Column(length=1)    // M or W로 성별 보기
    private String u_gender;

    @Column(length = 8)
    private String u_birth_day;

    @Column
    private String u_phone;

    @Column
    private String u_email;

    @Column //서비스 이용약관 동의
    private boolean u_svc_use_pcy_agmt_yn;

    @Column // 개인정보처리방침방침동의여부
    private boolean u_ps_info_proc_agme_yn;

    @Column  // 위치기반서비스동의여부
    private boolean u_loc_base_svc_agmt_yn;

    @Column
    private boolean u_sub_yn;

    @Column
    private boolean u_locked_yn;

    @Column
    private String u_last_connection;

    @Column
    private String u_local;

    @Column
    private int u_report_cnt;
}
