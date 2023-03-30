package com.cap.fatrip.repository;

import com.cap.fatrip.entity.MemberEntity;
import com.cap.fatrip.entity.OAuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<OAuthUserEntity, Long> {
    Optional<OAuthUserEntity> findByEmail(String memberEmail);
}
