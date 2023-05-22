package com.cap.fatrip.dto;

import com.cap.fatrip.entity.CCommentEntity;
import com.cap.fatrip.entity.CommentEntity;
import com.cap.fatrip.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CCommentDto {
    private long cc_key;
    private UserEntity user;
    private CommentEntity comment;
    private Date c_date;
    private int c_mod_yn;
    private String c_content;
    private int c_like_cnt;
    private int c_dislike_cnt;
    private int c_anonymous;

    public static CCommentDto of(CCommentEntity commentEntity){
        CCommentDto comment = new CCommentDto();
        comment.cc_key = commentEntity.getCc_key();
        comment.user = commentEntity.getUser();
        comment.comment = commentEntity.getComment();
        comment.c_date = commentEntity.getC_date();
        comment.c_mod_yn = commentEntity.getC_mod_yn();
        comment.c_content = commentEntity.getC_content();
        comment.c_like_cnt = commentEntity.getC_like_cnt();
        comment.c_dislike_cnt = commentEntity.getC_dislike_cnt();
        comment.c_anonymous = commentEntity.getC_anonymous();

        return comment;
    }
}
