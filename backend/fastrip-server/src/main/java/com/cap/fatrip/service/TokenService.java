package com.cap.fatrip.service;

import com.cap.fatrip.auth.TokenConstants;
import com.cap.fatrip.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Service
public class TokenService {
	private String secretKey = "awioejfasldncvknwklerj123ijr5sjadfasdf3928iklsdafkl";  // token-secret-key

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

	public Claims getClaim(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}
}