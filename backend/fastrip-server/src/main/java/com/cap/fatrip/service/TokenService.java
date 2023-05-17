package com.cap.fatrip.service;

import com.cap.fatrip.auth.TokenConstants;
import com.cap.fatrip.dto.UserDto;
import com.cap.fatrip.dto.outbound.LoginDto;
import com.cap.fatrip.util.ServiceUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenService {
	private String secretKey = "awioejfasldncvknwklerj123ijr5sjadfasdf3928iklsdafkl";  // token-secret-key
	private final ObjectMapper objectMapper;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String generateToken(UserDto user) {
		Claims claims = Jwts.claims().setSubject(user.getId());
		claims.put(TokenConstants.ROLE, user.getRole().name());
		claims.put(TokenConstants.EMAIL, user.getEmail());
		claims.put(TokenConstants.NICKNAME, user.getNickname());
		claims.put(TokenConstants.ID, user.getId());

		Date now = new Date();
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + TokenConstants.PERIOD_MILS))
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	public boolean verifyToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser()
					.setSigningKey(secretKey)
					.parseClaimsJws(token);
			return claims.getBody()
					.getExpiration()
					.after(new Date());
		} catch (Exception e) {
			return false;
		}
	}

	public void writeTokenResponse(HttpServletResponse response, String token, UserDto userDto)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");

		response.addHeader(TokenConstants.TOKEN, token);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setStatus(HttpStatus.OK.value());
		LoginDto loginDto = new LoginDto();
		ServiceUtil.copyObject(userDto, loginDto);

		var writer = response.getWriter();
		writer.println(objectMapper.writeValueAsString(loginDto));
		writer.flush();
	}


	public Claims getClaim(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}
}