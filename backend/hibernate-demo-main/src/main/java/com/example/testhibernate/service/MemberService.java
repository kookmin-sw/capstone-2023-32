package com.example.testhibernate.service;

import com.example.testhibernate.entity.MemberEntity;
import com.example.testhibernate.repo.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository userRepository;
    private final MemberRepository memberRepository;


    public MemberEntity getUserById(Long id) {
        Optional<MemberEntity> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }
    public MemberEntity getUserByUsername(String username) {
        Optional<MemberEntity> userOptional = userRepository.findByUsername(username);
        return userOptional.orElse(null);
    }
    public MemberEntity getUserByName(String name) {
        Optional<MemberEntity> userOptional = userRepository.findByName(name);
        return userOptional.orElse(null);
    }
    public MemberEntity getUserByNameAndIdLessThan(String name, Long id) {
        Optional<MemberEntity> userOptional = userRepository.findByNameAndAndPidIsLessThan(name, id);
        return userOptional.orElse(null);
    }

    public List<MemberEntity> findAll() {
        return memberRepository.findAll();
    }

    public MemberEntity createMember(MemberEntity member) {
        return memberRepository.save(member);
    }
}
