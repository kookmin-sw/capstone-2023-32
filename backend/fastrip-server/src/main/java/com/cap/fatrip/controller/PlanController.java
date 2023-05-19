package com.cap.fatrip.controller;

import com.cap.fatrip.dto.PPlanDto;
import com.cap.fatrip.dto.PlanDto;
import com.cap.fatrip.dto.inbound.PlanReqDto;
import com.cap.fatrip.dto.inbound.PlanDetailSaveDto;
import com.cap.fatrip.dto.inbound.PlanSaveDto;
import com.cap.fatrip.dto.outbound.PlanDetailDto;
import com.cap.fatrip.dto.outbound.PlanResDto;
import com.cap.fatrip.entity.PPlanEntity;
import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.entity.PlanTagEntity;
import com.cap.fatrip.entity.TagEntity;
import com.cap.fatrip.repository.PlanTagRepository;
import com.cap.fatrip.repository.TagRepository;
import com.cap.fatrip.service.PlanService;
import com.cap.fatrip.service.TagService;
import com.cap.fatrip.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plan")
@RequiredArgsConstructor
public class PlanController {
	private final PlanService planService;
	private final TagService tagService;
	private final TagRepository tagRepository;

	private final PlanTagRepository planTagRepository;

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

	@PutMapping("/save/{p_id}")
	public String update(@RequestBody PlanDetailSaveDto saveDto) {

		PlanEntity planEntity = planService.updatePlan(saveDto.getPlan());
		List<TagEntity> tagEntityList = new ArrayList<>();
		List<TagEntity> tagEntities = new ArrayList<>();
		List<String> tags = saveDto.getPlan().getTags();
		for(String tag : tags) {
			Optional<TagEntity> t = tagRepository.findByName(tag);
			if(t.isPresent()) {
				tagEntities.add(TagEntity.builder()   //tag내에 이미 있는 내용이 있을 시 PlanTag와의 연관성을 위해 따로 저장
						.name(tag).build());
			} else {
				tagEntityList.add(TagEntity.builder()
						.name(tag).build());

				tagEntities.add(TagEntity.builder()   //tag내에 이미 있는 내용이 있을 시 PlanTag와의 연관성을 위해 따로 저장
						.name(tag).build());
			}

		}
		tagRepository.saveAll(tagEntityList);

		List<PPlanDto> pplans = saveDto.getPplan();
		for (PPlanDto pplan : pplans) {
			planService.updatePplan(pplan,planEntity);
		}
		PlanTagEntity planTagEntity = new PlanTagEntity();
		planTagEntity.setPlan(PlanEntity.ofForSave(saveDto.getPlan()));
		for(TagEntity tagList : tagEntities) {
			planTagEntity.setTag(tagList);
			planTagRepository.save(planTagEntity);
		}

		System.out.println(saveDto);
		return "save success";
	}

}
