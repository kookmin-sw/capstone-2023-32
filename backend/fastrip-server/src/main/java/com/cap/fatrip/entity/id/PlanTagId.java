package com.cap.fatrip.entity.id;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class PlanTagId implements Serializable {
	private Long plan;
	private Long tag;
}
