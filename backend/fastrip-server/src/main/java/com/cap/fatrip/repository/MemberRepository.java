package com.cap.fatrip.repository;

import com.cap.fatrip.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByMemberEmail(String memberEmail);
}
