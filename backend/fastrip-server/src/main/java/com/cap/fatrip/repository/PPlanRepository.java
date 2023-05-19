package com.cap.fatrip.repository;

import com.cap.fatrip.entity.PPlanEntity;
import com.cap.fatrip.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PPlanRepository extends JpaRepository<PPlanEntity, String> {
    Optional<PPlanEntity> findByPlanAndSeq(PlanEntity plan, int seq);
}
