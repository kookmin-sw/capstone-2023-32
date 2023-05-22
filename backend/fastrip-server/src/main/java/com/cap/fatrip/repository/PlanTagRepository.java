package com.cap.fatrip.repository;

import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.entity.PlanTagEntity;
import com.cap.fatrip.entity.id.PlanTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanTagRepository extends JpaRepository<PlanTagEntity, PlanTagId> {
	void deleteAllByPlan(PlanEntity plan);
}
