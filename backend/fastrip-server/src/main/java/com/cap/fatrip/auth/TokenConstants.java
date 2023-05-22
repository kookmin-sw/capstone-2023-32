package com.cap.fatrip.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class TokenConstants {
	// claims
	public static final String ID = "id";
	public static final String NICKNAME = "nickname";
	public static final String EMAIL = "email";
	public static final String ROLE = "role";
	// header key
	public static final String TOKEN = "Auth";
	// period
	public static final long PERIOD_MILS = 1000L * 60 * 60 * 24 * 30;
}
