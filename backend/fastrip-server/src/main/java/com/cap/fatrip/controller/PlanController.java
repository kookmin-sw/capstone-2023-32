package com.cap.fatrip.controller;

import com.cap.fatrip.dto.inbound.PlanReqDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PlanController {
	@PostMapping("/plan")
	public void find(@RequestBody PlanReqDto planReqDto){

	}
}
