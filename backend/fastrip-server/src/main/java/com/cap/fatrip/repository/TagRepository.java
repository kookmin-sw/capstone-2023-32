package com.cap.fatrip.repository;

import com.cap.fatrip.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, String> {
}
