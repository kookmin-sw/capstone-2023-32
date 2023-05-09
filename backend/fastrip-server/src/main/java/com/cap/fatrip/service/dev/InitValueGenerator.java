package com.cap.fatrip.service.dev;

import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.entity.PurposeEntity;
import com.cap.fatrip.repository.PlanRepository;
import com.cap.fatrip.repository.PurposeRepository;
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
	private final PurposeRepository purposeRepository;

	@PostConstruct
	public void generate() {
		generatePlan();
		generatePurpose();
	}

	private void generatePurpose(){
		if (purposeRepository.count() !=0) {
			return;
		}
		String[] purposes = {"activity", "tourism", "rest"};
		List<PurposeEntity> purposeEntityList = new ArrayList<>();
		for (String purpose : purposes) {

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
