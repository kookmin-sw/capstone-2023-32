package com.cap.fatrip.controller;

import com.cap.fatrip.dto.inbound.PlanReqDto;
import com.cap.fatrip.dto.outbound.PlanResDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class PlanController {
	@PostMapping("/plan")
	public PlanResDto[] find(@RequestBody PlanReqDto planReqDto) {
		PlanResDto[] dtos = createDummyDtoList(planReqDto);
		return dtos;
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
