package com.cap.fatrip.entity;


import com.cap.fatrip.dto.inbound.PlanSaveDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plan")
public class PlanEntity extends TimeEntity {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "p_id") //계획 id
	private String id;

	@ManyToOne
	@JoinColumn(name = "user_id")   //fk
	private UserEntity user;
	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
	private List<PlanTagEntity> planTagEntities = new ArrayList<>();
	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
	private List<PPlanEntity> pPlanEntities = new ArrayList<>();
	@Column
	private Integer likes;
	@Column(name = "title")
	private String title;

	@Column(name = "comment")
	private String comment;
	@Column(name = "image")
	private String image;

	@PrePersist
	private void initLikes() {
		likes = likes == null ? 0 : likes;
	}


	public static PlanEntity ofForSave(PlanSaveDto planSaveDto) {
		PlanEntity planEntity = new PlanEntity();
		planEntity.title = planSaveDto.getTitle();
		planEntity.comment = planSaveDto.getComment();
		planEntity.image = planSaveDto.getImage();
		return planEntity;
	}

}
