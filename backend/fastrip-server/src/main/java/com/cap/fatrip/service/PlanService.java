package com.cap.fatrip.service;

import com.cap.fatrip.dto.PlanDto;
import com.cap.fatrip.dto.inbound.PlanReqDto;
import com.cap.fatrip.dto.outbound.PlanResDto;
import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PlanService {
	private final PlanRepository planRepository;

	public void savePlan(PlanDto planDto) {
		planRepository.save(PlanEntity.toPlanEntity(planDto));
	}

//	public PlanDto

	public List<PlanResDto> getPlans(PlanReqDto planReqDto) {
		String title = planReqDto.getTitle() == null ? "" : planReqDto.getTitle();
		List<String> tags = planReqDto.getTags() == null ? new ArrayList<>() : planReqDto.getTags();
		List<PlanEntity> planByTagsAndTitle = switch (tags.size()) {
			case 0 -> planRepository.findPlanByTagsAndTitle(title);
			case 1 -> planRepository.findPlanByTagsAndTitle(title, tags.get(0));
			case 2 -> planRepository.findPlanByTagsAndTitle(title, tags.get(0), tags.get(1));
			case 3 -> planRepository.findPlanByTagsAndTitle(title, tags.get(0), tags.get(1), tags.get(2));
			case 4 -> planRepository.findPlanByTagsAndTitle(title, tags.get(0), tags.get(1), tags.get(2), tags.get(3));
			case 5 ->
					planRepository.findPlanByTagsAndTitle(title, tags.get(0), tags.get(1), tags.get(2), tags.get(3), tags.get(4));
			case 6 ->
					planRepository.findPlanByTagsAndTitle(title, tags.get(0), tags.get(1), tags.get(2), tags.get(3), tags.get(4), tags.get(5));
			case 7 ->
					planRepository.findPlanByTagsAndTitle(title, tags.get(0), tags.get(1), tags.get(2), tags.get(3), tags.get(4), tags.get(5), tags.get(6));
			case 8 ->
					planRepository.findPlanByTagsAndTitle(title, tags.get(0), tags.get(1), tags.get(2), tags.get(3), tags.get(4), tags.get(5), tags.get(6), tags.get(7));
			case 9 ->
					planRepository.findPlanByTagsAndTitle(title, tags.get(0), tags.get(1), tags.get(2), tags.get(3), tags.get(4), tags.get(5), tags.get(6), tags.get(7), tags.get(8));
			case 10 ->
					planRepository.findPlanByTagsAndTitle(title, tags.get(0), tags.get(1), tags.get(2), tags.get(3), tags.get(4), tags.get(5), tags.get(6), tags.get(7), tags.get(8), tags.get(9));
			default -> new ArrayList<>();
		};
		List<PlanResDto> planResDtos = new ArrayList<>();
		for (PlanEntity planEntity : planByTagsAndTitle) {
			planResDtos.add(PlanResDto.of(planEntity));
		}
		return planResDtos;
	}
}
