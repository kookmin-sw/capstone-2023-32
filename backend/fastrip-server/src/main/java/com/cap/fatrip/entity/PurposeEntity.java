package com.cap.fatrip.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class PurposeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@OneToMany(mappedBy = "plan")
	private List<PlanPurposeEntity> plans;
}
