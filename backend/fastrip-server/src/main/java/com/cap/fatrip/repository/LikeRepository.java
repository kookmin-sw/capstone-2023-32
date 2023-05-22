package com.cap.fatrip.repository;

import com.cap.fatrip.entity.LikeEntity;
import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
	Optional<LikeEntity> findByPlanAndUser(PlanEntity plan, UserEntity userEntity);
}
