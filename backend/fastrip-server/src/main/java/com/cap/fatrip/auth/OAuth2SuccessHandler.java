package com.cap.fatrip.auth;

import com.cap.fatrip.dto.UserDto;
import com.cap.fatrip.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
	private final TokenService tokenService;
	private final UserRequestMapper userRequestMapper;
	private final ObjectMapper objectMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
		UserDto userDto = userRequestMapper.toDto(oAuth2User);

		// 최초 로그인이라면 회원가입 처리를 한다.

		Token token = tokenService.generateToken(userDto.getEmail(), "USER");
		log.info("{}", token);

		writeTokenResponse(response, token);
	}

	private void writeTokenResponse(HttpServletResponse response, Token token)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");

		response.addHeader("Auth", token.getToken());
		response.addHeader("Refresh", token.getRefreshToken());
		response.setContentType("application/json;charset=UTF-8");

		var writer = response.getWriter();
		writer.println(objectMapper.writeValueAsString(token));
		writer.flush();
	}
}
