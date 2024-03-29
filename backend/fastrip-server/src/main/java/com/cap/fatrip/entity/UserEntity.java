package com.cap.fatrip.entity;

import com.cap.fatrip.dto.UserDto;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor //기본 생성자 만들어줌
@AllArgsConstructor //기본 생성자 만들어줌
@Builder
@DynamicUpdate //update 할때 실제 값이 변경됨 컬럼으로만 update 쿼리를 만듬
@Entity //JPA Entity 임을 명시
@Setter @Getter //Lombok 어노테이션으로 getter
@Table(name = "user") //테이블 관련 설정 어노테이션
public class UserEntity extends TimeEntity {
	@Id
	private String id;

//	@Column(name = "password", nullable = false, length = 30)
//	private String password;

	@Column(name = "name", length = 20)
	private String name;

	@Column(name = "nickname", nullable = false, length = 20)
	private String nickname;

	@Column
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	@OneToMany(mappedBy = "user")
	private List<PlanEntity> planEntityList;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<LikeEntity> likeEntities;

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
		return userEntity;
	}
}