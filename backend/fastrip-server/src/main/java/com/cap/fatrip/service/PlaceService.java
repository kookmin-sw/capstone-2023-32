package com.cap.fatrip.service;

import com.cap.fatrip.dto.PPlanDto;
import com.cap.fatrip.dto.PlaceDto;
import com.cap.fatrip.dto.PlanDto;
import com.cap.fatrip.entity.PPlanEntity;
import com.cap.fatrip.entity.PlaceEntity;
//import com.cap.fatrip.repository.PPlanRepository;
import com.cap.fatrip.repository.PlaceRepository;
import com.cap.fatrip.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public void savePlace(PlaceDto placeDto) {
        placeRepository.save(PlaceEntity.toPlaceEntity(placeDto));
    }
}
