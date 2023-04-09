package com.cap.fatrip.dto;

import com.cap.fatrip.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {
	private String id;
	private String password;
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
	private Date last_connection;
	private String location;
	private String picture;
	private int report_cnt;
	private UserEntity.Role role;

	public static UserDto toUserDto(UserEntity userEntity) {
		UserDto user = new UserDto();

		user.id = userEntity.getId();
		user.password = userEntity.getPassword();
		user.name = userEntity.getName();
		user.nickname = userEntity.getNickname();
		user.gender = userEntity.getGender();
		user.birthday = userEntity.getBirthday();
		user.phone = userEntity.getPhone();
		user.email = userEntity.getEmail();
		user.svc_use_pcy_agmt_yn = userEntity.getSvc_use_pcy_agmt_yn();
		user.ps_info_proc_agmt_yn = userEntity.getPs_info_proc_agmt_yn();
		user.loc_base_svc_agmt_yn = userEntity.getLoc_base_svc_agmt_yn();
		user.sub_yn = userEntity.getSub_yn();
		user.locked_yn = userEntity.getLocked_yn();
		user.last_connection = userEntity.getLast_connection();
		user.location = userEntity.getLocation();
		user.report_cnt = userEntity.getReport_cnt();
		user.role = userEntity.getRole();

		return user;
	}
}