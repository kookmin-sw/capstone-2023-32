package com.cap.fatrip.service;

import com.cap.fatrip.dto.inbound.PlanReqDto;
import com.cap.fatrip.dto.inbound.PlanSaveDto;
import com.cap.fatrip.dto.outbound.PlanResDto;
import com.cap.fatrip.entity.*;
import com.cap.fatrip.repository.PPlanRepository;
import com.cap.fatrip.repository.PlanRepository;
import com.cap.fatrip.repository.PlanTagRepository;
import com.cap.fatrip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlanService {
	private final PlanRepository planRepository;
	private final PPlanRepository pplanRepository;
	private final UserRepository userRepository;
	private final PlanTagRepository planTagRepository;

	public List<PPlanEntity> savePplans(List<PPlanEntity> planEntityList) {
		return pplanRepository.saveAll(planEntityList);
	}

	public List<PlanTagEntity> associateTags(PlanEntity planEntity, List<TagEntity> tagEntities) {
		List<PlanTagEntity> planTagEntityList = tagEntities.stream().map(tagEntity -> new PlanTagEntity(planEntity, tagEntity)).toList();
		return planTagRepository.saveAll(planTagEntityList);
	}

	public PlanEntity savePlan(PlanSaveDto planDto) {
		PlanEntity planEntity = PlanEntity.ofForSave(planDto);
		UserEntity userEntity = userRepository.findById(planDto.getUserId()).orElseThrow(() -> {
			log.error("there's no such user id : {}", planDto.getUserId());
			return new NoSuchElementException("there's no user");
		});
		planEntity.setUser(userEntity);
		return planRepository.save(planEntity);
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

	public PlanEntity getPlanDetail(long planId) {
		return planRepository.findById(planId).orElseThrow(() -> {
			log.error("there's no such plan id : {}", planId);
			return new NoSuchElementException("there's no plan");
		});
	}
}
