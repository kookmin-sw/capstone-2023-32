package com.cap.fatrip.auth;

import com.cap.fatrip.dto.UserDto;
import com.cap.fatrip.entity.UserEntity;
import com.cap.fatrip.repository.UserRepository;
import com.cap.fatrip.service.TokenService;
import com.cap.fatrip.util.ServiceUtil;
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
//	http://localhost:8080/oauth2/authorization/google
	private final TokenService tokenService;
	private final UserRepository userRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
		String email = oAuth2User.getAttribute("email");
		UserEntity userEntity = userRepository.findByEmail(email).orElseThrow();
		UserDto userDto = new UserDto();
		ServiceUtil.copyObject(userEntity, userDto);

		String token = tokenService.generateToken(userDto);
		log.info("{}", token);

		tokenService.writeTokenResponse(response, token, userDto);
	}
}
