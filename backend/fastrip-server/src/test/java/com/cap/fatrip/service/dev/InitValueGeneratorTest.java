package com.cap.fatrip.service.dev;

import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.entity.PlanTagEntity;
import com.cap.fatrip.entity.TagEntity;
import com.cap.fatrip.repository.PlanRepository;
import com.cap.fatrip.repository.PlanTagRepository;
import com.cap.fatrip.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class InitValueGeneratorTest {
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private PlanTagRepository planTagRepository;
	Random random = new Random();

	@Test
	void saveRelationAndFind() {
		String[] tags = {"낭만", "파리", "유럽"};
//		TagEntity tagEntity = saveTag(tags[random.nextInt(10) % tags.length]);
		List<TagEntity> tagEntities = saveTags();
		PlanEntity planEntity = savePlan("이것이 파리다");

		PlanTagEntity rel = new PlanTagEntity();
		rel.setPlan(planEntity);
		for (TagEntity tagEntity : tagEntities) {
			rel.setTag(tagEntity);
			planTagRepository.save(rel);
		}
		planTagRepository.save(rel);

		List<PlanTagEntity> all = planTagRepository.findAll();
		List<PlanEntity> planByTag1 = planRepository.findPlanByTag2(tags[0], null);
		PlanEntity planEntity1 = planByTag1.get(0);
		List<PlanTagEntity> planTagEntities = planEntity1.getPlanTagEntities();
		System.out.println();
	}

	List<TagEntity> saveTags(){
		String[] tags = {"낭만", "파리", "유럽"};
		List<TagEntity> tagEntityList = new ArrayList<>();
		for (String tag : tags) {
			tagEntityList.add(TagEntity.builder()
					.name(tag)
					.build());
		}
		return tagRepository.saveAll(tagEntityList);
	}

	TagEntity saveTag(String tagName) {
		TagEntity tagEntity = TagEntity.builder()
				.name(tagName)
				.build();
		return tagRepository.save(tagEntity);
	}

	PlanEntity savePlan(String title) {
		PlanEntity planEntity = PlanEntity.builder()
				.title(title)
				.likes(random.nextInt(5, 55))
//				.open(random.nextInt(1, 10) % 2 == 1)
				.build();
		return planRepository.save(planEntity);
	}
}