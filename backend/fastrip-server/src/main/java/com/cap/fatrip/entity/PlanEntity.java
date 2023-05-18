package com.cap.fatrip.entity;


import com.cap.fatrip.dto.inbound.PlanSaveDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="p_id") //계획 id
	private long id;

	@ManyToOne
	@JoinColumn(name = "user_id")   //fk
	private UserEntity user;
	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
	private List<PlanTagEntity> planTagEntities = new ArrayList<>();
	@OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
	private List<PPlanEntity> pPlanEntities = new ArrayList<>();
	@Column
	@ColumnDefault("0")
	private int likes;
	//    @Column(name = "p_open")
//    private boolean open;
	@Column(name = "title")
	private String title;

	@Column(name = "comment")
	private String comment;
	@Column(name = "image")
	private String image;


	public static PlanEntity ofForSave(PlanSaveDto planSaveDto) {
		PlanEntity planEntity = new PlanEntity();
		planEntity.title = planSaveDto.getTitle();
		planEntity.comment = planSaveDto.getComment();
		planEntity.image = planSaveDto.getImage();
		return planEntity;
	}
}
