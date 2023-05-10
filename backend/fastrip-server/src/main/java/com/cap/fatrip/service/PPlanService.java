package com.cap.fatrip.service;

import com.cap.fatrip.dto.PPlanDto;
import com.cap.fatrip.entity.PPlanEntity;
import com.cap.fatrip.repository.PPlanRepository;
import com.cap.fatrip.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PPlanService {
    private final PPlanRepository pplanRepository;

    public void savePplan(PPlanDto pplanDto) {
        pplanRepository.save(PPlanEntity.toPPlanEntity(pplanDto));
    }
}
