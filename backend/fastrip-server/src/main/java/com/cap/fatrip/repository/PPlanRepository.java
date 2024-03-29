package com.cap.fatrip.repository;

import com.cap.fatrip.entity.PPlanEntity;
import com.cap.fatrip.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PPlanRepository extends JpaRepository<PPlanEntity, String> {
    void deleteAllByPlan(PlanEntity plan);
}
