package com.cap.fatrip.service.dev;

import com.cap.fatrip.controller.PlanController;
import com.cap.fatrip.dto.inbound.PlanDetailSaveDto;
import com.cap.fatrip.entity.UserEntity;
import com.cap.fatrip.repository.PlanRepository;
import com.cap.fatrip.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class InitValueGenerator {
	private final PlanRepository planRepository;
	private final UserRepository userRepository;
	private final PlanController planController;
	private final ObjectMapper objectMapper;

	@PostConstruct
	public void generate() throws IOException {
		if (planRepository.count() != 0) {
			return;
		}
		File plansFile = new File("C:\\Users\\정해준\\OneDrive\\바탕 화면\\backend\\fastrip-server\\etc\\plansInput.json");
		PlanDetailSaveDto[] planDetailSaveDtos = objectMapper.readValue(plansFile, PlanDetailSaveDto[].class);

		for (PlanDetailSaveDto planDetailSaveDto : planDetailSaveDtos) {
			String userId = planDetailSaveDto.getPlan().getUserId();
			UserEntity user = userRepository.findById(userId).orElse(UserEntity.builder().nickname(userId).role(UserEntity.Role.USER).build());
			UserEntity userEntity = userRepository.save(user);
			planDetailSaveDto.getPlan().setUserId(userEntity.getId());
			planController.save(planDetailSaveDto);
		}
	}
}
