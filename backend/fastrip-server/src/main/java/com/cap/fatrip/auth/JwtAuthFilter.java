package com.cap.fatrip.auth;

import com.cap.fatrip.dto.UserDto;
import com.cap.fatrip.entity.UserEntity;
import com.cap.fatrip.service.TokenService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthFilter extends GenericFilterBean {
	private final TokenService tokenService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String token = ((HttpServletRequest)request).getHeader("Auth");

		if (token != null && tokenService.verifyToken(token)) {
			Claims claims = tokenService.getUid(token);
			String email = (String) claims.get(Token.EMAIL);
			String nickname = (String) claims.get(Token.NICKNAME);
			String role = (String) claims.get(Token.ROLE);

			// DB연동을 안했으니 이메일 정보로 유저를 만듦.
			// todo: creating user process
			UserDto userDto = UserDto.builder()
					.email(email)
					.nickname(nickname)
					.role(UserEntity.Role.valueOf(role))
//					.picture("프로필 이미지에요")
					.build();

			Authentication auth = getAuthentication(userDto);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}

		chain.doFilter(request, response);
	}

	public Authentication getAuthentication(UserDto user) {
		return new UsernamePasswordAuthenticationToken(user, "",
				// todo: specify role.
				List.of(new SimpleGrantedAuthority(user.getRole().getValue())));
	}
}