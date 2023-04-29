package com.cap.fatrip.entity;

import com.cap.fatrip.dto.BoardDto;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long b_key;

    @ManyToOne
    @JoinColumn(name = "u_key",nullable = false)   //fk
    private UserEntity user;

    @Column(name = "b_title")
    private String b_title;

    @Column(name = "b_date",nullable = false)
    private Date b_date;

    @Column(name = "b_mod_date",nullable = false)
    private Date b_mod_date;

    @Column(name = "b_alarm_yn")
    private int b_alarm_yn;

    @Column(name = "b_mod_yn",columnDefinition = "tinyint not null default 0")
    private int b_mod_yn;

    @Column(name = "b_content")
    private String b_content;

    @Column(name = "b_report_cnt")
    private int b_report_cnt;

    @Column(name = "b_like_cnt")
    private int b_like_cnt;

    @Column(name = "b_noti_yn",columnDefinition = "tinyint not null default 0")
    private int b_noti_yn;

    @Column(name = "b_category",nullable = false)
    private String b_category;

    @Column(name = "b_blind",columnDefinition = "tinyint not null default 0")
    private int b_blind;

    @Column(name = "b_anonymous")
    private int b_anonymous;
    public static BoardEntity toBoardEntity(BoardDto boardDto){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.b_key = boardDto.getB_key();
        boardEntity.user = boardDto.getUser();
        boardEntity.b_title = boardDto.getB_title();
        boardEntity.b_date = boardDto.getB_date();
        boardEntity.b_mod_date = boardDto.getB_mod_date();
        boardEntity.b_content = boardDto.getB_content();
        boardEntity.b_alarm_yn = boardDto.getB_alarm_yn();
        boardEntity.b_mod_yn = boardDto.getB_mod_yn();
        boardEntity.b_report_cnt = boardDto.getB_report_cnt();
        boardEntity.b_like_cnt = boardDto.getB_like_cnt();
        boardEntity.b_noti_yn = boardDto.getB_noti_yn();
        boardEntity.b_category = boardDto.getB_category();
        boardEntity.b_blind = boardDto.getB_blind();
        boardEntity.b_anonymous = boardDto.getB_anonymous();
        return boardEntity;
    }

}

