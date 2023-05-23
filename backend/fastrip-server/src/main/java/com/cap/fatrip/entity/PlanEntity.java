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
	@Column
	private Integer likes;
	@Column
	private String title;
	@Column
	private String category;
	@Column
	private String comment;
	@Column
	private String image;
	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
	private List<PlanTagEntity> planTagEntities = new ArrayList<>();
	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
	private List<PPlanEntity> pPlanEntities = new ArrayList<>();
	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
	private List<LikeEntity> likeEntities = new ArrayList<>();

	@PrePersist
	private void initLikes() {
		likes = likes == null ? 0 : likes;
	}


	public static PlanEntity ofForSave(PlanSaveDto planSaveDto) {
		PlanEntity planEntity = new PlanEntity();
		planEntity.title = planSaveDto.getTitle();
		planEntity.comment = planSaveDto.getComment();
		planEntity.image = planSaveDto.getImage();
		planEntity.category = planSaveDto.getCategory();
		return planEntity;
	}

}
