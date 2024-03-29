package com.cap.fatrip.auth;

import com.cap.fatrip.dto.UserDto;
import com.cap.fatrip.entity.UserEntity;
import com.cap.fatrip.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AboutUsersTest {
	@Autowired
	TokenService tokenService;
	@Test
	void createTestJWT() {
		UserDto userDto = UserDto.builder()
				.id("Tanziro")
				.role(UserEntity.Role.USER)
				.email("jun")
				.nickname("nick")
				.build();
		System.out.println(tokenService.generateToken(userDto));
	}
}
