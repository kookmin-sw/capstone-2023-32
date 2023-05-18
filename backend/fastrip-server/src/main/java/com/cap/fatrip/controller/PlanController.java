package com.cap.fatrip.controller;

import com.cap.fatrip.dto.PPlanDto;
import com.cap.fatrip.dto.PlanDto;
import com.cap.fatrip.dto.inbound.PlanReqDto;
import com.cap.fatrip.dto.inbound.PlanDetailSaveDto;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
	public PlanDetailDto getPlanDetail(@RequestParam long id) throws Exception {
		PlanDetailDto planDetailDto = new PlanDetailDto();
		PlanEntity planDetail = planService.getPlanDetail(id);

		planDetailDto.setPlan(PlanDto.of(planDetail));
		planDetailDto.setPplan(planDetail.getPPlanEntities().stream().map(PPlanDto::of).toList());
		planDetailDto.getPlan().setTags(planDetail.getPlanTagEntities().stream().map(planTagEntity -> planTagEntity.getTag().getName()).toList());

		return planDetailDto;
	}

	@DeleteMapping(params = {"id"})
	public boolean deletePlan(@RequestParam long id) throws Exception {
		String userId = UserService.getUserFromAuth().getId();
		planService.deletePlan(id, userId);
		return true;
	}

	@PostMapping("/save")
	public String save(@RequestBody PlanDetailSaveDto saveDto) {
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

		return "save success";
	}

}
