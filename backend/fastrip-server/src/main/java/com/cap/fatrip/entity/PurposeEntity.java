package com.cap.fatrip.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "purpose")
public class PurposeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@OneToMany(mappedBy = "purpose", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<PlanPurposeEntity> plans;
}
