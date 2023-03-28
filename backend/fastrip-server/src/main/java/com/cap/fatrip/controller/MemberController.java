package com.cap.fatrip.controller;

import com.cap.fatrip.dto.MemberDto;
import com.cap.fatrip.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입
    private final MemberService memberService;



    @GetMapping("/member/save") //회원가입 페이지 요청
    public String saveForm() {

        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDto memberDto) {

        System.out.println("pass");
        System.out.println("memberDto = " + memberDto);
        memberService.save(memberDto);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }

    //로그인 처리 및 세션 저장
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDto memberDto, HttpSession session) {
        MemberDto loginResult = memberService.login(memberDto);
        if(loginResult != null){
            //login 성공
            //session에 email를 저장
            session.setAttribute("loginEmail",loginResult.getMemberEmail());
            return "main";
        } else {
            //login 실패
            return "login";
        }
    }

    //회원 조회
    @GetMapping("/member/")
    public String findAll(Model model) {
        List<MemberDto> memberDtoList = memberService.findAll();

        model.addAttribute("memberList",memberDtoList);
        return "list";
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
       MemberDto memberDto =  memberService.findById(id);
       model.addAttribute("member", memberDto);
       return "detail";
    }

    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model) {
        String myEamil = (String)session.getAttribute("loginEmail");
        MemberDto memberDto = memberService.updateForm(myEamil);
        model.addAttribute("updateMember", memberDto);
        return "update";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDto memberDto) {
        memberService.update(memberDto);
        return "redirect:/member/" + memberDto.getId();
    }

    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        memberService.deleteById(id);
        return "redirect:/member/";
    }

    @GetMapping("/member/logout")
    public String logut(HttpSession session) {
        session.invalidate();
        return "index";
    }
}





