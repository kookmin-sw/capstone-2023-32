package com.cap.fatrip.entity;

import com.cap.fatrip.entity.id.PlanTagId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PlanTagId.class)
@Table(name = "planTag")
public class PlanTagEntity {
	@Id
	@ManyToOne
	private PlanEntity plan;
	@Id
	@ManyToOne
	private TagEntity tag;
}
