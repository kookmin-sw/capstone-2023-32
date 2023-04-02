package com.cap.fatrip.dto;

import com.cap.fatrip.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDto {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    public static MemberDto toMemberDto(UserEntity userEntity) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(userEntity.getId());
        memberDto.setMemberEmail(userEntity.getMemberEmail());
        memberDto.setMemberPassword(userEntity.getMemberPassword());
        memberDto.setMemberName(userEntity.getMemberName());

        return memberDto;
    }
}
