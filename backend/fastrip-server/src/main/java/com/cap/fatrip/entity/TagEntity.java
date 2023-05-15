package com.cap.fatrip.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
	@ColumnDefault("0")
	private int count;
	@OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
	private List<PlanTagEntity> planTagEntities;
}
