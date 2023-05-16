package com.cap.fatrip.auth;

import com.cap.fatrip.dto.UserDto;
import com.cap.fatrip.service.TokenService;
import com.cap.fatrip.service.UserService;
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

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
		UserDto userDto = UserDto.of(oAuth2User);
		userService.saveAndFindUser(userDto);

		// todo: specify role.
		String token = tokenService.generateToken(userDto);
		log.info("{}", token);

		writeTokenResponse(response, token);
	}

	private void writeTokenResponse(HttpServletResponse response, String token)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");

		response.addHeader(TokenConstants.TOKEN, token);
		response.setContentType("application/json;charset=UTF-8");

		var writer = response.getWriter();
		writer.println(objectMapper.writeValueAsString(token));
		writer.flush();
	}
}
