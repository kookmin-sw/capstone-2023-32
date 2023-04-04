package com.cap.fatrip.dto;

import com.cap.fatrip.Role;
import com.cap.fatrip.entity.OAuthUserEntity;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserSessionDto implements Serializable {
	private String id;
	private String username;
	private String nickname;
	private String email;
	private Role role;
	private String modifiedDate;

	/* Entity -> dto */
	public UserSessionDto(OAuthUserEntity user) {
		this.id = user.getId();
		this.username = user.getName();
		this.nickname = user.getNickname();
		this.email = user.getEmail();
		this.role = user.getRole();
		this.modifiedDate = user.getModifiedDate();
	}
}