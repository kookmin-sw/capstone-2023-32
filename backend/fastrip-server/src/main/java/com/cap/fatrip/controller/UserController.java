package com.cap.fatrip.controller;

import com.cap.fatrip.dto.UserDto;
import com.cap.fatrip.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserController {
    //생성자 주입
    private final UserService userService;
    private static final String NAME = "name";

    @PostMapping("/signup")
    public String save(@RequestBody UserDto userDto) {
        boolean exist = userService.isExist(userDto.getId());
        if (exist) {
            return "id is already exist.";
        }
        userService.save(userDto);
        return "signup success";
    }

    @GetMapping(value = "/checkId", params = {"id"})
    public boolean isExist(@RequestParam String id) {
        return userService.isExist(id);
    }
    //회원 조회
    @GetMapping("/all")
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping(params = {"id"})
    public UserDto findById(@RequestParam String id) {
        return userService.findById(id);
    }

    @PostMapping("/update")
    public String update(@RequestBody UserDto userDto) {
        boolean exist = userService.isExist(userDto.getId());
        if (!exist) {
            return "the user is not exist";
        }
        userService.save(userDto);
        return "update success";
    }

    @GetMapping(value = "/delete", params = {"id"})
    public String deleteById(@RequestParam String id) {
        userService.deleteById(id);
        return "delete success";
    }

    @GetMapping("/member/logout")
    public String logut(HttpSession session) {
        session.invalidate();
        return "index";
    }
}





