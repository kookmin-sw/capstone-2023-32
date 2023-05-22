package com.cap.fatrip.entity;

import com.cap.fatrip.dto.CommentDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long c_key;

    @ManyToOne
    @JoinColumn(name = "id",nullable = false)   //fk
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "b_key",nullable = false)   //fk
    private BoardEntity board;

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

    public static CommentEntity toCommentEntity(CommentDto commentDto){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.c_key = commentDto.getC_key();
        commentEntity.user = commentDto.getUser();
        commentEntity.board = commentDto.getBoard();
        commentEntity.c_date = commentDto.getC_date();
        commentEntity.c_mod_yn = commentDto.getC_mod_yn();
        commentEntity.c_content = commentDto.getC_content();
        commentEntity.c_like_cnt = commentDto.getC_like_cnt();
        commentEntity.c_dislike_cnt = commentDto.getC_dislike_cnt();
        commentEntity.c_anonymous = commentDto.getC_anonymous();

        return commentEntity;
    }
}
