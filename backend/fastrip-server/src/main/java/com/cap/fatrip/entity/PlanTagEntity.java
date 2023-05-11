package com.cap.fatrip.entity;

import com.cap.fatrip.entity.id.PlanTagId;

import javax.persistence.*;

@Entity
@IdClass(PlanTagId.class)
public class PlanTagEntity {
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	private PlanEntity plan;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	private TagEntity tag;
}
