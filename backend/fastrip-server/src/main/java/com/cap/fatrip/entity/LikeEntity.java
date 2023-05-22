package com.cap.fatrip.entity;

import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Table(name = "likes")
public class LikeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private UserEntity user;
	@ManyToOne
	private PlanEntity plan;
}
