package com.cap.fatrip.controller;

import com.cap.fatrip.dto.PPlanDto;
import com.cap.fatrip.dto.PlaceDto;
import com.cap.fatrip.dto.PlanDto;
import com.cap.fatrip.dto.inbound.PlanReqDto;
import com.cap.fatrip.dto.inbound.savePlanDto;
import com.cap.fatrip.dto.outbound.PlanResDto;
import com.cap.fatrip.entity.PlanEntity;
import com.cap.fatrip.entity.PlanTagEntity;
import com.cap.fatrip.entity.TagEntity;
import com.cap.fatrip.repository.PlanTagRepository;
import com.cap.fatrip.repository.TagRepository;
import com.cap.fatrip.service.PPlanService;
import com.cap.fatrip.service.PlaceService;
import com.cap.fatrip.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/plan")
@RequiredArgsConstructor
public class PlanController {
	private final PlanService planService;
	private final PPlanService pplanService;
	private final TagRepository tagRepository;
	private final PlanTagRepository planTagRepository;

	//	@PostMapping("/all")
	@GetMapping("/all")
//	public List<PlanResDto> find(@RequestBody PlanReqDto planReqDto) {
	public List<PlanResDto> findAll() {
		PlanResDto[] dtos = createDummyDtoList();
		return List.of(dtos);
	}

	@PostMapping("/all")
	public List<PlanResDto> findAll(@RequestBody PlanReqDto planReqDto) {
		return planService.getPlans(planReqDto);
	}

	@GetMapping("/alls")
	public List<PlanDto> findAllsss() {
		return planService.getPlans();
	}


	private PlanResDto[] createDummyDtoList() {
		int cnt = 15;
		PlanResDto[] dtos = new PlanResDto[cnt];
		for (int i = 0; i < cnt; i++) {
			PlanResDto dummyDto = createDummyDto();
			dtos[i] = dummyDto;
		}
		return dtos;
	}

	@GetMapping(path = "savePlan", params = {"title", "tag1", "tag2"})
	public void savePlanTest(@RequestParam String title, @RequestParam String tag1, @RequestParam String tag2) {
		planService.savePlanTest(title, tag1, tag2);
	}

	@GetMapping(path = "findPlan", params = {"tag"})
	public void findPlanTest(@RequestParam String tag) {
		planService.findPlanTest(tag);
	}

	@PostMapping("/save")
	public String save(@RequestBody savePlanDto saveDto) {

		planService.savePlan(saveDto.getPlan());
		List<TagEntity> tagEntityList = new ArrayList<>();
		List<String> tags = saveDto.getTag();
		for(String tag : tags) {
			//if 절로 해당 tag가 안에 내용이 있는지 확인용
			tagEntityList.add(TagEntity.builder()
					.name(tag).build());
		}
		tagRepository.saveAll(tagEntityList);

		List<PPlanDto> pplans = saveDto.getPplan();
		for (PPlanDto pplan : pplans) {
			pplanService.savePplan(pplan);
		}
		PlanTagEntity planTagEntity = new PlanTagEntity();
		planTagEntity.setPlan(PlanEntity.toPlanEntity(saveDto.getPlan()));
		for(TagEntity tagList : tagEntityList) {
			planTagEntity.setTag(tagList);
			planTagRepository.save(planTagEntity);
		}

		System.out.println(saveDto);
		return "save success";
	}

	private PlanResDto createDummyDto() {
		String[] titles = {"낭만의 도시 파리", "힐링하세요~", "이건 잘못됐어...", "신사의 나라...?"};
		Random random = new Random();

		PlanResDto dto = new PlanResDto();
		dto.setLike(random.nextInt(5, 55));
		dto.setId(random.nextInt(20000));
		dto.setTitle(titles[random.nextInt(100) % titles.length] + "_" + random.nextInt(1, 10));
		dto.setUserId("cap_user_" + random.nextInt(100));
		dto.setTags(List.of(new String[]{"계획", "낭만", "관광"}));
		return dto;
	}


}
