package com.cap.fatrip.controller;

import com.cap.fatrip.dto.PlanDto;
import com.cap.fatrip.dto.inbound.PlanReqDto;
import com.cap.fatrip.dto.outbound.PlanResDto;
import com.cap.fatrip.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/plan")
@RequiredArgsConstructor
public class PlanController {
	private final PlanService planService;

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

	private PlanResDto createDummyDto() {
		Random random = new Random();

		PlanResDto dto = new PlanResDto();
		dto.setStar(random.nextDouble() * 3 + 2);
		dto.setPlanId(random.nextInt(20000));
		dto.setUserId("cap_user_" + random.nextInt(100));
		dto.setPurpose(new PlanReqDto.Purpose[]{PlanReqDto.Purpose.rest, PlanReqDto.Purpose.tourism});
		return dto;
	}
}
