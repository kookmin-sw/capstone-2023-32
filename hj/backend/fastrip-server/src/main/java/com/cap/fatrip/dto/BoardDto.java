package com.cap.fatrip.dto;

import com.cap.fatrip.entity.BoardEntity;
import com.cap.fatrip.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto implements Serializable {
    private long b_key;
    private UserEntity user;
    private String b_title;
    private Date b_date;
    private Date b_mod_date;
    private int b_alarm_yn;
    private int b_mod_yn;
    private String b_content;
    private int b_report_cnt;
    private int b_like_cnt;
    private int b_noti_yn;
    private String b_category;
    private int b_blind;
    private int b_anonymous;


    public static BoardDto of(BoardEntity boardEntity) {
        BoardDto board = new BoardDto();

        board.b_key = boardEntity.getB_key();
        board.user = boardEntity.getUser();
        board.b_title = boardEntity.getB_title();
        board.b_date = boardEntity.getB_date();
        board.b_mod_date = boardEntity.getB_mod_date();
        board.b_content = boardEntity.getB_content();
        board.b_alarm_yn = boardEntity.getB_alarm_yn();
        board.b_mod_yn = boardEntity.getB_mod_yn();
        board.b_report_cnt = boardEntity.getB_report_cnt();
        board.b_like_cnt = boardEntity.getB_like_cnt();
        board.b_noti_yn = boardEntity.getB_noti_yn();
        board.b_category = boardEntity.getB_category();
        board.b_blind = boardEntity.getB_blind();
        board.b_anonymous = boardEntity.getB_anonymous();
        return board;
    }
}
