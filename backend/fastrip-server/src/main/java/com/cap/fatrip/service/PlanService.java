package com.cap.fatrip.service;

import com.cap.fatrip.dto.PlanDto;
import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.*;
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

	public void savePlan(PlanDto planDto) {
		planRepository.save(PlanEntity.toPlanEntity(planDto));
	}

}
