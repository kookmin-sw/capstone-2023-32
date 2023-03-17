package com.example.testhibernate.controller;

import com.example.testhibernate.entity.MemberEntity;
import com.example.testhibernate.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // JSON 형태 결과값을 반환해줌 (@ResponseBody가 필요없음)
@RequiredArgsConstructor
@RequestMapping("/v1") // API version
public class MemberController {
    private final MemberService memberService;

    /**
     * 멤버 조회
     */
    @GetMapping("member")
    public List<MemberEntity> findAllMember() {
        return memberService.findAll();
    }
    @GetMapping(value = "member", params = {"id"})
    public MemberEntity getMemberById(@RequestParam Long id) {
        return memberService.getUserById(id);
    }
    @GetMapping(value = "member", params = {"username"})
    public MemberEntity getMemberByUsername(@RequestParam String username) {
        return memberService.getUserByUsername(username);
    }
    @GetMapping(value = "member", params = {"name"})
    public MemberEntity getMemberByName(@RequestParam String name) {
        return memberService.getUserByName(name);
    }
    @GetMapping(value = "member", params = {"name", "id"})
    public MemberEntity getMemberByNameAndIdLessThan(@RequestParam String name, @RequestParam Long id) {
        return memberService.getUserByNameAndIdLessThan(name, id);
    }

    /**
     * 회원가입
     * body :
     * {
     *     "username" : "email",
     *     "name" : "name"
     * }
     */
    @PostMapping("member")
    public MemberEntity signUp(@RequestBody MemberEntity member) {
        return memberService.createMember(member);
    }
}
