package com.cap.fatrip.repository;

import com.cap.fatrip.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, String> {
	Optional<TagEntity> findByName(String tagName);
	void deleteByName(String tagName);
}
