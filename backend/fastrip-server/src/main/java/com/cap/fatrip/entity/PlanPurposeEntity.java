package com.cap.fatrip.entity;

import javax.persistence.*;

@Entity
public class PlanPurposeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
//	@ManyToOne
//	@JoinColumn(name = "plan_id"))
//	private PlanEntity plan;
}
