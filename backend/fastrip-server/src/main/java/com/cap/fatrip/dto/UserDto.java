package com.cap.fatrip.dto;

import com.cap.fatrip.entity.UserEntity;
import com.cap.fatrip.util.ServiceUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {
	private String id;
//	private String password;
	private String name;
	private String nickname;
	private int gender;
	private Date birthday;
	private String phone;
	private String email;
	private int svc_use_pcy_agmt_yn;
	private int ps_info_proc_agmt_yn;
	private int loc_base_svc_agmt_yn;
	private int sub_yn;
	private int locked_yn;
	private String location;
	private String picture;
	private int report_cnt;
	private UserEntity.Role role;

	public static UserDto of(UserEntity userEntity) {
		UserDto user = new UserDto();
		ServiceUtil.copyObject(userEntity, user);

		return user;
	}

	public static UserDto of(OAuth2User oAuth2User) {
		var attributes = oAuth2User.getAttributes();
		return UserDto.builder()
//				.id((String) attributes.get("id"))
				.role((UserEntity.Role) attributes.get("role"))
				.email((String) attributes.get("email"))
				.name((String) attributes.get("name"))
				.nickname((String) attributes.get("nickname"))
				.picture((String) attributes.get("picture"))
				.build();
	}
}