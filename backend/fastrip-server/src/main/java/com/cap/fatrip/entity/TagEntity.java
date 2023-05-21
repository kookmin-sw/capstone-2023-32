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
	@Column
	private Integer count;
	@OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
	private List<PlanTagEntity> planTagEntities;

	@PrePersist
	private void initCount() {
		count = count == null ? 1 : count;
	}
}
