package com.cap.fatrip.auth;

import com.cap.fatrip.dto.UserDto;
import com.cap.fatrip.dto.outbound.LoginDto;
import com.cap.fatrip.entity.UserEntity;
import com.cap.fatrip.repository.UserRepository;
import com.cap.fatrip.service.TokenService;
import com.cap.fatrip.service.UserService;
import com.cap.fatrip.util.ServiceUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
	private final TokenService tokenService;
	private final ObjectMapper objectMapper;
	private final UserService userService;
	private final UserRepository userRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
		String email = oAuth2User.getAttribute("email");
		UserEntity userEntity = userRepository.findByEmail(email).orElseThrow();
		UserDto userDto = new UserDto();
		ServiceUtil.copyObject(userEntity, userDto);

		String token = tokenService.generateToken(userDto);
		log.info("{}", token);

		writeTokenResponse(response, token, userDto);
	}

	private void writeTokenResponse(HttpServletResponse response, String token, UserDto userDto)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");

		response.addHeader(TokenConstants.TOKEN, token);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		LoginDto loginDto = new LoginDto();
		ServiceUtil.copyObject(userDto, loginDto);

		var writer = response.getWriter();
		writer.println(objectMapper.writeValueAsString(loginDto));
		writer.flush();
	}
}
