package com.cap.fatrip.service;

import com.cap.fatrip.auth.Token;
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

	public Token generateToken(UserDto user) {
		long tokenPeriod = 1000L * 60L * 10L;
		long refreshPeriod = 1000L * 60L * 60L * 24L * 30L * 3L;

		Claims claims = Jwts.claims().setSubject(user.getId());
		claims.put(Token.ROLE, user.getRole().getValue());
		claims.put(Token.EMAIL, user.getEmail());
		claims.put(Token.NICKNAME, user.getNickname());

		Date now = new Date();
		return new Token(
				Jwts.builder()
						.setClaims(claims)
						.setIssuedAt(now)
						.setExpiration(new Date(now.getTime() + tokenPeriod))
						.signWith(SignatureAlgorithm.HS256, secretKey)
						.compact(),
				Jwts.builder()
						.setClaims(claims)
						.setIssuedAt(now)
						.setExpiration(new Date(now.getTime() + refreshPeriod))
						.signWith(SignatureAlgorithm.HS256, secretKey)
						.compact());
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

	public Claims getUid(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}
}