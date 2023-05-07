package com.cap.fatrip.service;

import com.cap.fatrip.dto.PlanDto;
import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PlanService {
	private final PlanRepository planRepository;
	public List<PlanDto> getPlans(){
		List<PlanEntity> planEntityList = planRepository.findAll();
		Stream<PlanDto> planDtoStream = planEntityList.stream().map(PlanDto::of);
		return planDtoStream.toList();
	}

	@PostConstruct
	public void randomSave() {
		if (planRepository.findAll().size() != 0) {
			return;
		}
		Random random = new Random();
		List<PlanEntity> planEntityList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			planEntityList.add(
					PlanEntity.builder()
							.userId("jun" + (random.nextInt(20) + 1))
							.starTotal(44 + random.nextInt(22, 46))
							.starCnt(9)
							.open(true)
							.cost(random.nextInt(44, 88))
							.build()
			);
		}
		List<PlanEntity> planEntities = planRepository.saveAll(planEntityList);
	}
}
