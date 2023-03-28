package com.cap.fatrip.service;

import com.cap.fatrip.entity.MemberEntity;
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
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDto);
        memberRepository.save(memberEntity);
    }

    public MemberDto login(MemberDto memberDto) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDto.getMemberEmail());
        if (byMemberEmail.isPresent()){
            //조회결과 있음
            MemberEntity memberEntity = byMemberEmail.get();
            if(memberEntity.getMemberPassword().equals(memberEntity.getMemberPassword())) {
                //비밀번호 일치
                MemberDto dto = MemberDto.toMemberDto(memberEntity);

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
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();

        for(MemberEntity memberEntity: memberEntityList) {
            memberDtoList.add(MemberDto.toMemberDto(memberEntity));
        }
        return memberDtoList;
    }

    public MemberDto findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if(optionalMemberEntity.isPresent()) {
            return MemberDto.toMemberDto((optionalMemberEntity.get()));
        } else {
            return null;
        }
    }

    public MemberDto updateForm(String myEamil) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEamil);
        if(optionalMemberEntity.isPresent()) {
            return MemberDto.toMemberDto(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public void update(MemberDto memberDto) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDto));
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}
