package com.cap.fatrip.auth;

import com.cap.fatrip.dto.UserDto;
import com.cap.fatrip.entity.UserEntity;
import com.cap.fatrip.repository.UserRepository;
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
	private final UserRepository userRepository;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String token = ((HttpServletRequest)request).getHeader(TokenConstants.TOKEN);

		if (token != null && tokenService.verifyToken(token)) {
			Claims claims = tokenService.getClaim(token);
			String email = (String) claims.get(TokenConstants.EMAIL);
			String id = (String) claims.get(TokenConstants.ID);
			String nickname = (String) claims.get(TokenConstants.NICKNAME);
			String role = (String) claims.get(TokenConstants.ROLE);

			nickname = userRepository.findById(id).map(UserEntity::getNickname).orElse(nickname);
			if (nickname == null || nickname.isEmpty()) {
				nickname = "temp_nickname";
			}

			// todo: creating user process
			UserDto userDto = UserDto.builder()
					.id(id)
					.email(email)
					.nickname(nickname)
					.role(UserEntity.Role.valueOf(role))
					.build();

			Authentication auth = getAuthentication(userDto);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}

		chain.doFilter(request, response);
	}

	public Authentication getAuthentication(UserDto user) {
		return new UsernamePasswordAuthenticationToken(user, "",
				// todo: specify role.
				List.of(new SimpleGrantedAuthority(user.getRole().name())));
	}
}