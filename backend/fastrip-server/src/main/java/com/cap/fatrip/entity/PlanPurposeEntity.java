package com.cap.fatrip.entity;

import javax.persistence.*;

@Entity
public class PlanPurposeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private PlanEntity plan;
	@ManyToOne(fetch = FetchType.LAZY)
	private PurposeEntity purpose;
}
