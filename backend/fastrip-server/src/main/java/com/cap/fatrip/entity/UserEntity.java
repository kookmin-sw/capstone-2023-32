package com.cap.fatrip.entity;

import com.cap.fatrip.dto.MemberDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "user")
public class UserEntity {
    //자동 번호 선정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) //unique제약 조건
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    @Column
    private String phoneNumber;

    public static UserEntity toUserEntity(MemberDto memberDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setMemberEmail(memberDto.getMemberEmail());
        userEntity.setMemberPassword(memberDto.getMemberPassword());
        userEntity.setMemberName(memberDto.getMemberName());
        return userEntity;
    }

    public static UserEntity toUpdateUserEntity(MemberDto memberDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(memberDto.getId());
        userEntity.setMemberEmail(memberDto.getMemberEmail());
        userEntity.setMemberPassword(memberDto.getMemberPassword());
        userEntity.setMemberName(memberDto.getMemberName());
        return userEntity;
    }

}
