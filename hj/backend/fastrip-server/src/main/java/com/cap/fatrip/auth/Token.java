package com.cap.fatrip.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class Token {
	private String token;
	private String refreshToken;
	public static final String NICKNAME = "nickname";
	public static final String EMAIL = "email";
	public static final String ROLE = "role";

	public Token(String token, String refreshToken) {
		this.token = token;
		this.refreshToken = refreshToken;
	}
}
