package com.cap.fatrip.entity;

import com.cap.fatrip.dto.UserDto;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor //기본 생성자 만들어줌
@AllArgsConstructor //기본 생성자 만들어줌
@Builder
@DynamicUpdate //update 할때 실제 값이 변경됨 컬럼으로만 update 쿼리를 만듬
@Entity //JPA Entity 임을 명시
@Setter @Getter //Lombok 어노테이션으로 getter
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(name = "email", columnNames = {"email"})}) //테이블 관련 설정 어노테이션
public class UserEntity extends TimeEntity {
	@Id @GeneratedValue(generator = "uuid2")
	@GenericGenerator(name="uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(36)")
	private String id;

//	@Column(name = "password", nullable = false, length = 30)
//	private String password;

	@Column(name = "name", nullable = false, length = 20)
	private String name;

	@Column(name = "nickname", nullable = false, length = 20)
	private String nickname;

	@Column(name = "gender", columnDefinition = "tinyint not null default 0")
	private int gender;

	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "phone", length = 40)
	private String phone;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "svc_use_pcy_agmt_yn", columnDefinition = "tinyint not null default 0")
	private int svc_use_pcy_agmt_yn;

	@Column(name = "ps_info_proc_agmt_yn", columnDefinition = "tinyint not null default 0")
	private int ps_info_proc_agmt_yn;

	@Column(name = "loc_base_svc_agmt_yn", columnDefinition = "tinyint not null default 0")
	private int loc_base_svc_agmt_yn;

	@Column(name = "sub_yn", columnDefinition = "tinyint not null default 0")
	private int sub_yn;

	@Column(name = "locked_yn", columnDefinition = "tinyint not null default 0")
	private int locked_yn;

	@Column(name = "location")
	private String location;

	@Column(name = "report_cnt", columnDefinition = "int not null default 0")
	private int report_cnt;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	/* 소셜로그인시 이미 등록된 회원이라면 수정날짜만 업데이트하고
	 * 기존 데이터는 그대로 보존하도록 예외처리 */
	public UserEntity updateModifiedDate() {
//		this.onPreUpdate();
		return this;
	}

	@Getter
	public enum Role {
		USER, ADMIN
	}

	public static UserEntity of(UserDto userDto){
		UserEntity userEntity = new UserEntity();
//		userEntity.id = userDto.getId();
//		userEntity.password = userDto.getPassword();
		userEntity.name = userDto.getName();
		userEntity.email = userDto.getEmail();
		userEntity.phone = userDto.getPhone();
		return userEntity;
	}
}