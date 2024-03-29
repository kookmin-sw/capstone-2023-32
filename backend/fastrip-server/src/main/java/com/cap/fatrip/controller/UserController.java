package com.cap.fatrip.controller;

import com.cap.fatrip.dto.UserDto;
import com.cap.fatrip.dto.outbound.LoginDto;
import com.cap.fatrip.entity.UserEntity;
import com.cap.fatrip.repository.UserRepository;
import com.cap.fatrip.service.TokenService;
import com.cap.fatrip.service.UserService;
import com.cap.fatrip.util.ServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/user")
public class UserController {
	//생성자 주입
	private final UserService userService;
	private final TokenService tokenService;
	private final UserRepository userRepository;
	private static final String NAME = "name";

	@GetMapping("/test")
	public void test() {
		UserDto userFromAuth = UserService.getUserFromAuth();
		System.out.println();
	}

	@PostMapping("/testUser")
	public String createTestUser(@RequestBody LoginDto loginDto) {
		UserEntity userEntity = UserEntity.builder()
				.id(loginDto.getId())
				.nickname(loginDto.getNickname() == null ? "test_nickname" : loginDto.getNickname())
				.role(UserEntity.Role.USER)
				.email("test@gmail.com")
				.build();
		UserEntity user = userRepository.findById(loginDto.getId()).orElse(userEntity);
		userRepository.save(userEntity);
		UserDto userDto = new UserDto();
		ServiceUtil.copyObject(user, userDto);
		return tokenService.generateToken(userDto);
	}

	@GetMapping(path = "changeNickname", params = "nickname")
	public void changeNickname(@RequestParam String nickname, HttpServletResponse response) throws IOException {
		UserEntity userEntity = userService.changeNickname(nickname);
		if (!userEntity.getId().isEmpty()) {
			UserDto userDto = UserDto.of(userEntity);
			String token = tokenService.generateToken(userDto);
			tokenService.writeTokenResponse(response, token, userDto);
		}

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
	}

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





