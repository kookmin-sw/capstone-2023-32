package com.cap.fatrip.service;

import com.cap.fatrip.dto.PlanDto;
import com.cap.fatrip.dto.inbound.PlanReqDto;
import com.cap.fatrip.dto.outbound.PlanResDto;
import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.entity.PlanTagEntity;
import com.cap.fatrip.entity.TagEntity;
import com.cap.fatrip.repository.PlanRepository;
import com.cap.fatrip.repository.PlanTagRepository;
import com.cap.fatrip.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Random;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PlanService {
	private final PlanRepository planRepository;
	private final TagRepository tagRepository;
	private final PlanTagRepository planTagRepository;

	public List<PlanDto> getPlans() {
		List<PlanEntity> planEntityList = planRepository.findAll();
		Stream<PlanDto> planDtoStream = planEntityList.stream().map(PlanDto::of);
		return planDtoStream.toList();
	}

	public void savePlan(PlanDto planDto) {
		planRepository.save(PlanEntity.toPlanEntity(planDto));
	}


	public void savePlanTest(String title, String tag1, String tag2) {
		Random random = new Random();
		List<String> tags = new ArrayList<>(List.of(tag1, tag2));
		PlanEntity planEntity = PlanEntity.builder()
				.title(title)
				.like(random.nextInt(5, 55))
				.open(random.nextInt(1, 10) % 2 == 1)
				.userId("test_id_" + random.nextInt(1, 20))
				.build();
		planRepository.save(planEntity);
		PlanTagEntity rel = new PlanTagEntity();
		rel.setPlan(planEntity);
		for (String tag : tags) {
			TagEntity tagEntity = saveTagTest(tag);
			rel.setTag(tagEntity);
			planTagRepository.save(rel);
		}
	}

	public TagEntity saveTagTest(String tag) {
		TagEntity tagEntity = TagEntity.builder()
				.name(tag).build();
		Optional<TagEntity> tagEntityOptional = tagRepository.findByName(tag);
		return tagEntityOptional.orElseGet(() -> tagRepository.save(tagEntity));
	}

	public void findPlanTest(String tag) {
		List<PlanEntity> planByTag1 = planRepository.findPlanByTag1(tag);
		PlanEntity planEntity = planByTag1.get(0);
		List<PlanTagEntity> planTagEntities = planEntity.getPlanTagEntities();
		System.out.println();
	}

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

	private void findPlans(PlanReqDto planReqDto) {
		String title = planReqDto.getTitle();
		List<String> tags = planReqDto.getTags();
//		planRepository.findPlanByTagsAndTitle()
	}
}
