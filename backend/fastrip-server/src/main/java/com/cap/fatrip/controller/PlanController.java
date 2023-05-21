package com.cap.fatrip.controller;

import com.cap.fatrip.dto.PPlanDto;
import com.cap.fatrip.dto.PlanDto;
import com.cap.fatrip.dto.inbound.*;
import com.cap.fatrip.dto.outbound.PlanDetailDto;
import com.cap.fatrip.dto.outbound.PlanResDto;
import com.cap.fatrip.entity.PPlanEntity;
import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.entity.PlanTagEntity;
import com.cap.fatrip.entity.TagEntity;
import com.cap.fatrip.service.PlanService;
import com.cap.fatrip.service.TagService;
import com.cap.fatrip.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Slf4j
@RequestMapping("/api/plan")
@RequiredArgsConstructor
public class PlanController {
	private final PlanService planService;
	private final TagService tagService;

	@PostMapping("/all")
	public List<PlanResDto> findAll(@RequestBody PlanReqDto planReqDto) {
		return planService.getPlans(planReqDto);
	}

	@GetMapping(params = {"id"})
	public PlanDetailDto getPlanDetail(@RequestParam String id) throws Exception {
		PlanDetailDto planDetailDto = new PlanDetailDto();
		PlanEntity planDetail = planService.getPlanDetail(id);

		planDetailDto.setPlan(PlanDto.of(planDetail));
		planDetailDto.setPplan(planDetail.getPPlanEntities().stream().map(PPlanDto::of).toList());
		planDetailDto.getPlan().setTags(planDetail.getPlanTagEntities().stream().map(planTagEntity -> planTagEntity.getTag().getName()).toList());

		return planDetailDto;
	}

	@DeleteMapping(params = {"id"})
	public boolean deletePlan(@RequestParam String id) throws Exception {
		String userId = UserService.getUserFromAuth().getId();
		planService.deletePlan(id, userId);
		return true;
	}

	@PostMapping("/save")
	public void save(@RequestBody PlanDetailSaveDto saveDto) {
		// plan 저장
		PlanEntity planEntity = planService.savePlan(saveDto.getPlan());
		// pplan 저장
		List<PPlanEntity> pPlanEntities = saveDto.getPplan().stream().map(pPlanDto -> {
			PPlanEntity pPlanEntity = PPlanEntity.of(pPlanDto);
			pPlanEntity.setPlan(planEntity);
			return pPlanEntity;
		}).toList();
		List<PPlanEntity> pPlanEntityList = planService.savePplans(pPlanEntities);
		// tag 저장
		List<TagEntity> tagEntities = tagService.saveTags(saveDto.getPlan().getTags());

		// plan <--> tag 연관관계 저장.
		List<PlanTagEntity> planTagEntities = planService.associateTags(planEntity, tagEntities);
		// plan <--> pplan 연관관계 저장은 이미 위에서 함. n : m 이 아니기 때문에 추가적인 매핑 작업 불필요.

		log.info("save plan success");
	}

	@PutMapping("/update")
	@Transactional
	public void update(@RequestBody PlanDetailUpdateDto saveDto) throws Exception {
		PlanUpdateDto planDto = saveDto.getPlan();
		PlanEntity planEntity = planService.updatePlan(planDto);

		planService.updateTags(planEntity, planDto);

		planService.updatePplans(planEntity, saveDto.getPplan());

		log.info("update plan success.");
	}

}
