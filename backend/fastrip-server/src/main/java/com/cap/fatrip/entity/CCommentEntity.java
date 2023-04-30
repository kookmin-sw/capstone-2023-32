package com.cap.fatrip.entity;

import com.cap.fatrip.dto.CCommentDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "ccomment")
public class CCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cc_key;

    @ManyToOne
    @JoinColumn(name = "id",nullable = false)   //fk
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "c_key",nullable = false)   //fk
    private CommentEntity comment;

    @Column(name = "c_date",nullable = false)
    private Date c_date;

    @Column(name = "c_mod_yn",columnDefinition = "tinyint not null default 0")
    private int c_mod_yn;

    @Column(name = "c_content")
    private String c_content;

    @Column(name = "c_like_cnt")  //비추천은 뺴면 어떨지..
    private int c_like_cnt;

    @Column(name = "c_dislike_cnt")  //비추천은 뺴면 어떨지..
    private int c_dislike_cnt;
    @Column(name = "c_anonymous")
    private int c_anonymous;

    public static CCommentEntity toCCommentEntity(CCommentDto commentDto){
        CCommentEntity commentEntity = new CCommentEntity();
        commentEntity.cc_key = commentDto.getCc_key();
        commentEntity.user = commentDto.getUser();
        commentEntity.comment = commentDto.getComment();
        commentEntity.c_date = commentDto.getC_date();
        commentEntity.c_mod_yn = commentDto.getC_mod_yn();
        commentEntity.c_content = commentDto.getC_content();
        commentEntity.c_like_cnt = commentDto.getC_like_cnt();
        commentEntity.c_dislike_cnt = commentDto.getC_dislike_cnt();
        commentEntity.c_anonymous = commentDto.getC_anonymous();

        return commentEntity;
    }
}
