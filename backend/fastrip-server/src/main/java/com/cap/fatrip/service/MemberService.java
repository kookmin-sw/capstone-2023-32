package com.cap.fatrip.service;

import com.cap.fatrip.entity.UserEntity;
import com.cap.fatrip.repository.MemberRepository;
import com.cap.fatrip.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    //1.dto->entity 변환
    //2.repository의 save 메소드 호출
    public void save(MemberDto memberDto){
        UserEntity userEntity = UserEntity.toUserEntity(memberDto);
        memberRepository.save(userEntity);
    }

    public MemberDto login(MemberDto memberDto) {
        Optional<UserEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDto.getMemberEmail());
        if (byMemberEmail.isPresent()){
            //조회결과 있음
            UserEntity userEntity = byMemberEmail.get();
            if(userEntity.getMemberPassword().equals(userEntity.getMemberPassword())) {
                //비밀번호 일치
                MemberDto dto = MemberDto.toMemberDto(userEntity);

                return dto;
            } else {
                //비밀 번호 불일치
                return null;
            }
        } else {
            //결과가 없음
            return null;
        }
    }

    public List<MemberDto> findAll() {
        List<UserEntity> userEntityList = memberRepository.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();

        for(UserEntity userEntity : userEntityList) {
            memberDtoList.add(MemberDto.toMemberDto(userEntity));
        }
        return memberDtoList;
    }

    public MemberDto findById(Long id) {
        Optional<UserEntity> optionalMemberEntity = memberRepository.findById(id);
        if(optionalMemberEntity.isPresent()) {
            return MemberDto.toMemberDto((optionalMemberEntity.get()));
        } else {
            return null;
        }
    }

    public MemberDto updateForm(String myEamil) {
        Optional<UserEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEamil);
        if(optionalMemberEntity.isPresent()) {
            return MemberDto.toMemberDto(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public void update(MemberDto memberDto) {
        memberRepository.save(UserEntity.toUpdateUserEntity(memberDto));
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}
