package com.cap.fatrip.dto;

import com.cap.fatrip.entity.BoardEntity;
import com.cap.fatrip.entity.CommentEntity;
import com.cap.fatrip.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private long c_key;
    private UserEntity user;
    private BoardEntity board;
    private Date c_date;
    private int c_mod_yn;
    private String c_content;
    private int c_like_cnt;
    private int c_dislike_cnt;
    private int c_anonymous;

    public static CommentDto of(CommentEntity commentEntity){
        CommentDto comment = new CommentDto();
        comment.c_key = commentEntity.getC_key();
        comment.user = commentEntity.getUser();
        comment.board = commentEntity.getBoard();
        comment.c_date = commentEntity.getC_date();
        comment.c_mod_yn = commentEntity.getC_mod_yn();
        comment.c_content = commentEntity.getC_content();
        comment.c_like_cnt = commentEntity.getC_like_cnt();
        comment.c_dislike_cnt = commentEntity.getC_dislike_cnt();
        comment.c_anonymous = commentEntity.getC_anonymous();

        return comment;
    }
}
