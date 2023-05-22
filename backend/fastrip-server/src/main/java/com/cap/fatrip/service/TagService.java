package com.cap.fatrip.service;

import com.cap.fatrip.dto.outbound.TagResDto;
import com.cap.fatrip.entity.TagEntity;
import com.cap.fatrip.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TagService {
	private final TagRepository tagRepository;


	public List<TagResDto> getPopularTags() {
		List<TagEntity> top10Entities = tagRepository.findTop10ByOrderByCountDesc();
		List<TagResDto> tagResDtos = new ArrayList<>();
		for (TagEntity topEntity : top10Entities) {
			tagResDtos.add(TagResDto.of(topEntity));
		}
		return tagResDtos;
	}

	public List<TagEntity> saveTags(List<String> tagList) {
		Set<String> tags = new HashSet<>(tagList);
		List<TagEntity> tagEntityList = tags.stream().map(tag -> {
					Optional<TagEntity> tagEntityOptional = tagRepository.findByName(tag);
					if (tagEntityOptional.isPresent()) {
						TagEntity tagEntity = tagEntityOptional.get();
						tagEntity.setCount(tagEntity.getCount() + 1);
						return tagEntity;
					}
					return TagEntity.builder().name(tag).build();
				}
		).toList();
		return tagRepository.saveAll(tagEntityList);
	}
}
