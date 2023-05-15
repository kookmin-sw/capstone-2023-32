package com.cap.fatrip.service;

import com.cap.fatrip.dto.outbound.TagResDto;
import com.cap.fatrip.entity.TagEntity;
import com.cap.fatrip.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {
	private final TagRepository tagRepository;
	// todo: plan 생성시 tag count 증가 구현.
	//  initValueGenerator 참조.
	public TagEntity saveTagTest(String tag) {
		TagEntity tagEntity = TagEntity.builder()
				.name(tag).build();
		Optional<TagEntity> tagEntityOptional = tagRepository.findByName(tag);
		return tagEntityOptional.orElseGet(() -> tagRepository.save(tagEntity));
	}

	public List<TagResDto> getPopularTags() {
		List<TagEntity> top10Entities = tagRepository.findTop10ByOrderByCountDesc();
		List<TagResDto> tagResDtos = new ArrayList<>();
		for (TagEntity topEntity : top10Entities) {
			tagResDtos.add(TagResDto.of(topEntity));
		}
		return tagResDtos;
	}
}
