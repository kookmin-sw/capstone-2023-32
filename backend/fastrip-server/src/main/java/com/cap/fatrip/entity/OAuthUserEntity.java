package com.cap.fatrip.entity;

import com.cap.fatrip.Role;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 만들어줌
@AllArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 만들어줌
@Builder
@DynamicUpdate //update 할때 실제 값이 변경됨 컬럼으로만 update 쿼리를 만듬
@Entity //JPA Entity 임을 명시
@Getter //Lombok 어노테이션으로 getter
@Table(name = "user") //테이블 관련 설정 어노테이션
public class OAuthUserEntity extends TimeEntity {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "nickname", nullable = false)
	private String nickname;

	@Column(name = "gender", nullable = false)
	private int gender;

	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "svc_use_pcy_agmt_yn", nullable = false)
	private int svc_use_pcy_agmt_yn;

	@Column(name = "ps_info_proc_agmt_yn", nullable = false)
	private int ps_info_proc_agmt_yn;

	@Column(name = "loc_base_svc_agmt_yn", nullable = false)
	private int loc_base_svc_agmt_yn;

	@Column(name = "sub_yn", nullable = false)
	private int sub_yn;

	@Column(name = "locked_yn", nullable = false)
	private int locked_yn;

	@Column(name = "last_connection", nullable = false)
	private Date last_connection;

	@Column(name = "create_time", nullable = false)
	private Date create_time;

	@Column(name = "location", nullable = true)
	private String location;

	@Column(name = "report_cnt", nullable = false)
	private int report_cnt;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;


	/* 회원정보 수정을 위한 set method*/
	public void modify(String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
	}

	public OAuthUserEntity update(String name, String email) {
		this.name = name;
		this.email = email;
		return this;
	}

	/* 소셜로그인시 이미 등록된 회원이라면 수정날짜만 업데이트하고
	 * 기존 데이터는 그대로 보존하도록 예외처리 */
	public OAuthUserEntity updateModifiedDate() {
		this.onPreUpdate();
		return this;
	}

	public String getRoleValue() {
		return this.role.getValue();
	}
}