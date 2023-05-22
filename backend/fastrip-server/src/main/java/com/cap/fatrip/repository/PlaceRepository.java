package com.cap.fatrip.repository;

import com.cap.fatrip.entity.PlaceEntity;
import com.cap.fatrip.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<PlaceEntity, String> {
}
