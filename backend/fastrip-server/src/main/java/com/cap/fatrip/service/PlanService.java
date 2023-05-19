package com.cap.fatrip.service;

import com.cap.fatrip.dto.PPlanDto;
import com.cap.fatrip.dto.PlanDto;
import com.cap.fatrip.dto.inbound.PlanDetailSaveDto;
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
	public void updatePplan(PPlanDto pplanDto,PlanEntity plan) {
		Optional<PPlanEntity> pplanEntity = pplanRepository.findByPlanAndSeq(plan, pplanDto.getP_seq());
		if (pplanEntity.isPresent()) {
			PPlanEntity pplan = pplanEntity.get();
			pplan.setP_country(pplanDto.getP_country());
			pplan.setP_post(pplanDto.getP_post());
			pplan.setP_name(pplanDto.getP_name());
			pplan.setP_locate(pplanDto.getP_locate());
			pplan.setP_name(pplanDto.getP_name());
			pplanRepository.save(pplan);
		} else {
			pplanRepository.save(PPlanEntity.of(pplanDto));
		}






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

	public PlanEntity updatePlan(PlanSaveDto planDto) {
		UserEntity userEntity = userRepository.findById(planDto.getUserId()).orElseThrow(() -> {
					log.error("there's no such user id : {}", planDto.getUserId());
					return new NoSuchElementException("there's no user");
		});
		Optional<PlanEntity> planEntity = planRepository.findByUserAndTitle(userEntity,planDto.getTitle());

		if(planEntity.isPresent()) {
			PlanEntity p = planEntity.get();
			p.setTitle(planDto.getTitle());
			p.setComment(planDto.getComment());
			return planRepository.save(p);
		} else {
			return planRepository.save(PlanEntity.ofForSave(planDto));
		}




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

	public PlanEntity getPlanDetail(String planId) throws Exception {
		return planRepository.findById(planId).orElseThrow(() -> throwPlanException(planId));
	}

	public void deletePlan(String id, String userId) throws Exception {
		PlanEntity planEntity = planRepository.findById(id).orElseThrow(() -> throwPlanException(id));
		if (planEntity.getUser().getId().equals(userId)) {
			planRepository.deleteById(id);
		} else {
			throw new Exception("해당 유저는 이 계획의 소유자가 아닙니다.");
		}
	}

	public Exception throwPlanException(String planId) {
		log.error("there's no such plan id : {}", planId);
		return new NoSuchElementException("there's no plan");
	}
}
