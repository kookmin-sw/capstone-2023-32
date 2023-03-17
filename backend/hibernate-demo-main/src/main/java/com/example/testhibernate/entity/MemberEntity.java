package com.example.testhibernate.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity(name="member") // 테이블 명을 작성
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;

    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @Column(nullable = false, length = 100)
    private String name;

}
