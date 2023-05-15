package com.cap.fatrip.service.dev;

import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.entity.PlanTagEntity;
import com.cap.fatrip.entity.TagEntity;
import com.cap.fatrip.entity.id.PlanTagId;
import com.cap.fatrip.repository.PlanRepository;
import com.cap.fatrip.repository.PlanTagRepository;
import com.cap.fatrip.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class InitValueGenerator {
	private final PlanRepository planRepository;
	private final TagRepository tagRepository;
	private final PlanTagRepository planTagRepository;

	@PostConstruct
	public void generate() {
		Random random = new Random();
		List<PlanEntity> planEntities = generatePlan();
		List<TagEntity> tagEntities = generateTag();
		if (planEntities == null || tagEntities == null) {
			return;
		}

		PlanTagEntity planTagEntity = new PlanTagEntity();
		for (PlanEntity planEntity : planEntities) {
			planTagEntity.setPlan(planEntity);
			for (int i = 0; i < random.nextInt(tagEntities.size()); i++) {
				TagEntity tag = tagEntities.get(random.nextInt(tagEntities.size()));
				planTagEntity.setTag(tag);
				Optional<PlanTagEntity> relOptional = planTagRepository.findById(new PlanTagId(planEntity.getId(), tag.getId()));
				if (relOptional.isPresent()) {
					continue;
				}
				tag.setCount(tag.getCount() + 1);
				tagRepository.save(tag);
				planTagRepository.save(planTagEntity);
			}
		}
	}

	private List<TagEntity> generateTag() {
		if (tagRepository.count() != 0) {
			return null;
		}
		String[] tags = {"낭만", "파리", "휴양", "지하철", "파리", "샌드위치"};
		List<TagEntity> tagEntityList = new ArrayList<>();
		for (String tag : tags) {
			tagEntityList.add(TagEntity.builder()
					.name(tag).build());
		}
		return tagRepository.saveAll(tagEntityList);
	}

	private List<PlanEntity> generatePlan() {
		if (planRepository.count() != 0) {
			return null;
		}
		String[] titles = {"낭만의 도시 파리", "유럽이 좋다", "이게 맞아??", "그런데 지금...", "유럽 지하철", "파리엔 파리가 많아"};
		Random random = new Random();
		List<PlanEntity> planEntityList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			planEntityList.add(
					PlanEntity.builder()
							.title(titles[random.nextInt(titles.length)] + "_" + random.nextInt(20))
							.userId("jun" + (random.nextInt(20) + 1))
							.like(random.nextInt(3, 55))
							.open(true).build()
			);
		}
		return planRepository.saveAll(planEntityList);
	}
}
