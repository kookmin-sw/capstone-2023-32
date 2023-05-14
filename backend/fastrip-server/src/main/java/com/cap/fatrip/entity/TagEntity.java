package com.cap.fatrip.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tag")
public class TagEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@OneToMany(mappedBy = "tag", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<PlanTagEntity> planTagEntities;
}
