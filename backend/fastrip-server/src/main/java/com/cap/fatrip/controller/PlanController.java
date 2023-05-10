package com.cap.fatrip.controller;

import com.cap.fatrip.dto.PPlanDto;
import com.cap.fatrip.dto.PlaceDto;
import com.cap.fatrip.dto.PlanDto;
import com.cap.fatrip.dto.inbound.PlanReqDto;
import com.cap.fatrip.dto.inbound.savePlanDto;
import com.cap.fatrip.dto.outbound.PlanResDto;
import com.cap.fatrip.service.PPlanService;
import com.cap.fatrip.service.PlaceService;
import com.cap.fatrip.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/plan")
@RequiredArgsConstructor
public class PlanController {
	private final PlanService planService;
	private PPlanService pplanService;
	private PlaceService placeService;

	@Autowired
	public PlanController(PlanService planService, PPlanService pplanService, PlaceService placeService) {
		this.planService = planService;
		this.pplanService = pplanService;
		this.placeService = placeService;
	}

	@PostMapping
	public List<PlanResDto> find(@RequestBody PlanReqDto planReqDto) {
		PlanResDto[] dtos = createDummyDtoList(planReqDto);
		return List.of(dtos);
	}

	@GetMapping("/all")
	public List<PlanDto> findAll(@RequestBody PlanReqDto planReqDto) {
		return planService.getPlans();
	}


	private PlanResDto[] createDummyDtoList(PlanReqDto planReqDto) {
		int cnt = 15;
		PlanResDto[] dtos = new PlanResDto[cnt];
		for (int i = 0; i < cnt; i++) {
			PlanResDto dummyDto = createDummyDto();
			dtos[i] = dummyDto;
		}
		return dtos;
	}

	@PostMapping("/save")
	public String save( @RequestBody savePlanDto saveDto) {

		planService.savePlan(saveDto.getPlan());
		//long p_id = saveDto.getPlan().getP_id();
		List<PlaceDto> places = saveDto.getPlace();
		for(PlaceDto place : places ) {
			placeService.savePlace(place);
		}

		List<PPlanDto> pplans = saveDto.getPplan();
		for(PPlanDto pplan : pplans ) {
			pplanService.savePplan(pplan);
		}

		System.out.println(saveDto);
		return "save success";
	}

	private PlanResDto createDummyDto() {
		Random random = new Random();

		PlanResDto dto = new PlanResDto();
		dto.setLike(random.nextInt(5, 55));
		dto.setPlanId(random.nextInt(20000));
		dto.setUserId("cap_user_" + random.nextInt(100));
		dto.setTag(new PlanReqDto.Tag[]{PlanReqDto.Tag.rest, PlanReqDto.Tag.tourism});
		return dto;
	}
}
