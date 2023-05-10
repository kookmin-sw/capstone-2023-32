package com.cap.fatrip.service.dev;

import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.entity.TagEntity;
import com.cap.fatrip.repository.PlanRepository;
import com.cap.fatrip.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class InitValueGenerator {
	private final PlanRepository planRepository;
	private final TagRepository tagRepository;

	@PostConstruct
	public void generate() {
		generatePlan();
		generateTag();
	}

	private void generateTag(){
		if (tagRepository.count() !=0) {
			return;
		}
		String[] tags = {"activity", "tourism", "rest"};
		List<TagEntity> tagEntityList = new ArrayList<>();
		for (String tag : tags) {

		}
	}

	private void generatePlan(){
		if (planRepository.count() != 0) {
			return;
		}
		Random random = new Random();
		List<PlanEntity> planEntityList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			planEntityList.add(
					PlanEntity.builder()
							.userId("jun" + (random.nextInt(20) + 1))
							.like(9)
							.open(true)
							.build()
			);
		}
		List<PlanEntity> planEntities = planRepository.saveAll(planEntityList);
	}
}
