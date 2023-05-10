package com.cap.fatrip.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tag")
public class TagEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@OneToMany(mappedBy = "tag", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<PlanTagEntity> plans;
}
