package com.cap.fatrip.repository;

import com.cap.fatrip.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PlanRepository extends JpaRepository<PlanEntity, Long> {
}
