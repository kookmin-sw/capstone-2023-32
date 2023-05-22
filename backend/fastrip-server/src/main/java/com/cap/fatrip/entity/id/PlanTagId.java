package com.cap.fatrip.entity.id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PlanTagId implements Serializable {
	private String plan;
	private Long tag;
}
