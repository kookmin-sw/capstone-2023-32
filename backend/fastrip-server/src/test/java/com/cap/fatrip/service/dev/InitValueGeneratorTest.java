package com.cap.fatrip.service.dev;

import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.entity.TagEntity;
import com.cap.fatrip.repository.PlanRepository;
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
	Random random = new Random();

	@Test
	void saveTag() {
		TagEntity tagEntity = TagEntity.builder()
				.name("activity")
				.build();
		TagEntity tag = tagRepository.save(tagEntity);
		System.out.println(tag);
	}

	@Test
	void savePlan() {
		String[] tags = {"낭만", "파리", "유럽"};
		List<TagEntity> tagEntityList = new ArrayList<>();
		for (int i = 0; i < tags.length; i++) {
			tagEntityList.add(TagEntity.builder()
					.plans(new ArrayList<>())
					.name("낭만")
					.build());
		}
		List<TagEntity> tagEntities = tagRepository.saveAll(tagEntityList);
		PlanEntity planEntity = PlanEntity.builder()
				.tags(new ArrayList<>())
				.like(random.nextInt(5, 55))
				.open(random.nextInt(1, 10) % 2 == 1)
				.userId("test_id_" + random.nextInt(1, 20))
				.build();
		PlanEntity plan = planRepository.save(planEntity);
	}
}